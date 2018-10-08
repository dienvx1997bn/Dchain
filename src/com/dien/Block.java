package com.dien;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data; //our data will be a simple message.
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;

    //Block Constructor.
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }

    //Calculate new hash based on blocks contents
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }
    /*
    Tạo block mới sao cho khi hash block này thì được mã hash có độ dài số 0 ở đầu mã tương ứng với số difficulty
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;                   //Tăng dần giá trị noce để hash ra giá trị mới. Về bản chất là tìm ra số nunce mỗi lần hast
            hash = calculateHash();
        }
        System.out.print("Block Mined!!! : " + hash);
        //hiển thị giá trị noce
        System.out.print("\tnonce: " + nonce);
        //hiển thị thời gian thực hiện đào
        Date currentDate = new Date(timeStamp);
        DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss");
        System.out.println("\tTime: " + df.format(currentDate));




    }
}
