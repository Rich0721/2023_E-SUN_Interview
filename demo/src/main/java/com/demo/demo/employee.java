package com.demo.demo;

public class employee {
    
    private String employeeId;
    private String Name;
    private String email;
    private String seatInfo;
    private int floorNo;
    private int seatNo;
    
    
    public employee(String id, String name, String email, String seatInfo, int floorNo, int seatNo){
        this.employeeId = id;
        this.Name = name;
        this.email = email;
        this.seatInfo = seatInfo;
        this.floorNo = floorNo;
        this.seatNo = seatNo;
    }

    public String getEmployeeId(){
        return employeeId;
    }

    public String getName(){
        return Name;
    }

    public String getEmail(){
        return email;
    }

    public String getSeatInfo(){
        return seatInfo;
    }
    
    public int getFloorNo() {
        return floorNo;
    }


    public int getSeatNo() {
        return seatNo;
    }

    public void setEmployeeId(String employeeId){
        this.employeeId = employeeId;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSeatInfo(String seatInfo){
        this.seatInfo = seatInfo;
    }
}
