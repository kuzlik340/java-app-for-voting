package com.example.uvs.Voting_logic;
import java.lang.Math;

public class VotingProcess {
  private static int people;
  public static int voting(){
        people = (int)(Math.random()*100000);
        return people;
  }


}
