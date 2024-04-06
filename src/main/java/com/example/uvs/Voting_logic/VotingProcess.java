package com.example.uvs.Voting_logic;
import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;

public class VotingProcess {
  public static List<Integer> voting(int idVote){
       //method to see how many people voted for some option in this voting
        List<Integer> people;
        people = DataBaseConnection.DataBaseInterface.getVotes(idVote);
        return people;
  }
  public static boolean checkIfVoted(int idVote, String login){
       //method to see if user already voted for this voting
      return DataBaseConnection.DataBaseInterface.checkIfVoted(login, idVote);
  }
  public static boolean checkIfVoted3times(int idVote){
      //this method checks if voting card was already clicked three times that we can show the result of voting
      //probably it will be much better do it with the Timer library, but I used this method here
      //it means if voting was already clicked 3 times than it will end and will show the result
      int counter = DataBaseConnection.DataBaseInterface.checkIfVoted3times(idVote);
      if(counter <= 2){
          return false;
      }
      return true;
  }


}
