package com.example.uvs.Voting_logic;

import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class VotingProcess {
    private static final Random random = new Random();
    private static final ConcurrentHashMap<Integer, Long> startTimeMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, Integer> durationMap = new ConcurrentHashMap<>();

    // Runnable that handles the timer logic
    private static class VotingTimer implements Runnable {
        private int idVote;

        public VotingTimer(int idVote) {
            this.idVote = idVote;
        }

        @Override
        public void run() {
            System.out.println("Started timer");
            try {
                // Calculate delay: 0 to 120 seconds (0 to 2 minutes)
                int delayInSeconds = random.nextInt(121);
                durationMap.put(idVote, delayInSeconds);
                startTimeMap.put(idVote, System.currentTimeMillis());

                System.out.println("Timer set for " + delayInSeconds + " seconds for ID: " + idVote);
                Thread.sleep(delayInSeconds * 1000);
                endVoting(idVote);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Timer interrupted for voting ID " + idVote);
            } finally {
                // Clean up after the timer ends or is interrupted
                startTimeMap.remove(idVote);
                durationMap.remove(idVote);
            }
        }
    }

    // Method to end voting and set the counter to 3
    private static void endVoting(int idVote) {
        DataBaseConnection.DataBaseInterface.setCounter(idVote, 3);
        System.out.println("Voting ended for ID " + idVote);
    }

    public static void startVotingTimer(int idVote) {
        new Thread(new VotingTimer(idVote)).start();
    }

    public static List<Integer> voting(int idVote) {
        startVotingTimer(idVote);  // Simplified for example; consider adding logic to prevent retriggering the timer unnecessarily.
        List<Integer> people = DataBaseConnection.DataBaseInterface.getVotes(idVote);
        return people;
    }

    public static boolean checkIfVoted(int idVote, String login) {
        return DataBaseConnection.DataBaseInterface.checkIfVoted(login, idVote);
    }

    public static boolean checkIfVoted3times(int idVote) {
        int counter = DataBaseConnection.DataBaseInterface.checkIfVoted3times(idVote);
        return counter > 2;
    }

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
