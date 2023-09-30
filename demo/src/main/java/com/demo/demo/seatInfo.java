package com.demo.demo;

public class seatInfo {

    

    private String floorSeatSeq;
    private int floorNo;
    private int seatNo;
    
    public String getFloorSeatSeq() {
        return floorSeatSeq;
    }
    
    public int getFloorNo() {
        return floorNo;
    }


    public int getSeatNo() {
        return seatNo;
    }

    public seatInfo(String floorSeatSeq, int floorNo, int seatNo){
        this.floorSeatSeq = floorSeatSeq;
        this.floorNo = floorNo;
        this.seatNo = seatNo;
    }
}
