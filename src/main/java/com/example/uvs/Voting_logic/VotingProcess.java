package com.example.uvs.Voting_logic;
import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;

public class VotingProcess {
  public static List<Integer> voting(int idVote){
        List<Integer> people;
        people = DataBaseConnection.DataBaseInterface.getVotes(idVote);
        return people;
  }
  public static boolean checkIfVoted(int idVote, String login){
       return DataBaseConnection.DataBaseInterface.checkIfVoted(login, idVote);
  }
  public static boolean checkIfVoted3times(int idVote){
      int counter = DataBaseConnection.DataBaseInterface.checkIfVoted3times(idVote);
      if(counter <= 2){
          return false;
      }
      return true;
  }


}
