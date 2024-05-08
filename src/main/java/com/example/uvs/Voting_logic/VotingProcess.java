package com.example.uvs.Voting_logic;

import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * The VotingProcess class handles the voting logic.
 */
public class VotingProcess {
    private static final Random random = new Random();
    private static final ConcurrentHashMap<Integer, Long> startTimeMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, Integer> durationMap = new ConcurrentHashMap<>();

    /**
     * Method to end voting and set Vote counter as 1 so we will know that this vote ended.
     *
     * @param idVote The ID of the voting.
     */
    private static void endVoting(int idVote) {
        DataBaseConnection.DataBaseInterface.setVoted(idVote, 1);
        System.out.println("Voting ended for ID " + idVote);
    }

    /**
     * Method to start the voting timer for a given voting ID.
     * Also in this method was implemented multithreading and lambda function.
     * Multithreading is used to handle lots of timers for different votings.
     * @param idVote The ID of the voting.
     */
    public static void startVotingTimer(int idVote) {
        Thread newThread = new Thread(() -> {
            System.out.println("Started timer");
            try {
                int delayInSeconds = random.nextInt(121);  // Calculate delay: 0 to 120 seconds (0 to 2 minutes)
                durationMap.put(idVote, delayInSeconds);
                startTimeMap.put(idVote, System.currentTimeMillis());

                System.out.println("Timer set for " + delayInSeconds + " seconds for ID: " + idVote);
                Thread.sleep(delayInSeconds * 1000);
                endVoting(idVote);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Timer interrupted for voting ID " + idVote);
            } finally {
                startTimeMap.remove(idVote);
                durationMap.remove(idVote);
            }
        });
        newThread.start();
    }

    /**
     * Method to find number of people that voted for all options.
     *
     * @param idVote The ID of the voting.
     * @return The list of people voted for all options separated in list.
     */
    public static List<Integer> voting(int idVote) {
        return DataBaseConnection.DataBaseInterface.getVotes(idVote);
    }

    /**
     * Method to check if a user has already voted for a given voting ID.
     *
     * @param idVote The ID of the voting.
     * @param login  The login of the user.
     * @return True if the user has already voted, false otherwise.
     */
    public static boolean checkIfVoted(int idVote, String login) {
        return DataBaseConnection.DataBaseInterface.checkIfVoted(login, idVote);
    }

    /**
     * Method to check if the voting has ended by reaching the remaining time.
     *
     * @param idVote The ID of the voting.
     * @return True if the voting has ended, false otherwise.
     */
    public static boolean checkIfVoteEnded(int idVote) {
        int counter = DataBaseConnection.DataBaseInterface.checkIfVoteEnded(idVote);
        return counter == 1;
    }

    /**
     * Method to get the remaining time for a voting ID.
     *
     * @param idVote The ID of the voting.
     * @return The remaining time as a string.
     */
    public static String getRemainingTime(int idVote) {
        Long startTime = startTimeMap.get(idVote);
        Integer duration = durationMap.get(idVote);
        if (startTime == null || duration == null) {
            return "No active timer for this voting ID.";
        } else {
            long elapsedTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
            long remainingTime = duration - elapsedTime;
            if (remainingTime < 0) {
                return "Timer has already expired for this voting ID.";
            }
            return "Remaining time for voting ID " + idVote + ": " + remainingTime + " seconds.";
        }
    }
}
