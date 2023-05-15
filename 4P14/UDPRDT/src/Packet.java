import java.io.Serializable;

/**
 * This class defines a Packet Object
 *
 */
public class Packet implements Serializable {

    int ACK, seqNo;
    long checksum;
    String payload;

    public Packet(int ACK, int seqNo, long checksum, String payload){
        this.ACK = ACK;
        this.seqNo = seqNo;
        this.checksum = checksum;
        this.payload = payload;
    }//constructor
}//Packet
