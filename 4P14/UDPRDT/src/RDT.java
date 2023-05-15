import java.util.zip.Adler32;

/**
 * This class implements the methods necessary for a Reliable Data Transfer (RDT) in the Application Layer
 *
 * COSC 4P14 - A2
 *
 * @author Sawyer Fenwick
 * @author Daniel Figueroa
 * @version 1.0
 */
public class RDT {

    int expectedReceiverACK = 0;        //expected ACK

    Adler32 ad = new Adler32();
    Packet retransmit;

    public RDT(){

    }//constructor

    public RDT(String hostname, int dest_port, int src_port){

    }//constructor2

    /**
     * Defines a RDT 2.2 Receiver (see FSM in Assignment Answer Sheet)
     *
     * @param rcvpkt
     */
    public void receiver(Packet rcvpkt){
        if(expectedReceiverACK == 0){
            if(rdt_rcv(rcvpkt, 0)){
                String data = extract(rcvpkt);      //extract payload
                deliver_data(data);                 //deliver data to Application Layer
                String ack = "ACK-0";
                ad.update(ack.getBytes());
                Packet sndpkt = make_pkt(1,0,ad.getValue(),ack); //make ACK
                udt_send(sndpkt);                   //send ACK
                retransmit = sndpkt;
                expectedReceiverACK = 1;            //now expecting ACK 1
            }
            else{
                udt_send(retransmit);               //send previous ACK
            }
        }
        else{
            if(rdt_rcv(rcvpkt, 1)){
                String data = extract(rcvpkt);      //extract payload
                deliver_data(data);                 //deliver data to Application Layer
                String ack = "ACK-1";
                ad.update(ack.getBytes());
                Packet sndpkt = make_pkt(1,1,ad.getValue(),ack); //make ACK
                udt_send(sndpkt);                   //send ACK
                retransmit = sndpkt;
                expectedReceiverACK = 0;
            }
            else{
                udt_send(retransmit);           //send previous ACK
            }
        }
    }//receiver

    /**
     * Returns TRUE if Packet is NOT corrupt and IS the appropriate ACK
     *
     * @param rcvpkt Packet
     * @param ACK expected ACK
     * @return TRUE
     */
    public boolean rdt_rcv(Packet rcvpkt, int ACK){
        return isNotCorrupt(rcvpkt) && isACK(rcvpkt, ACK);     //packet is NOT corrupt and is the CORRECT ACK
    }//rdt_rcv

    /**
     * Resends the previous packet on timeout
     */
    public void timeout(){
        udt_send(retransmit);   //exception catch will be my timer
    }//timeout

    /**
     * Checks if a packet is corrupt by calculating its checksum and checking it against the given checksum
     *
     * @param rcvpkt Packet
     * @return TRUE if checksums match
     */
    public boolean isNotCorrupt(Packet rcvpkt){
        ad.update(rcvpkt.payload.getBytes());
        long checksum = ad.getValue();
        return rcvpkt.checksum == checksum;
    }//isNotCorrupt

    /**
     * Checks if the packet is an ACK
     * @param rcvpkt Packet
     * @param ACK value of ACK
     * @return TRUE if ACK matches ACK
     */
    public boolean isACK(Packet rcvpkt, int ACK){
        return rcvpkt.ACK == ACK;
    }//isACK

    /**
     * Checks what ACK number is send in Packet
     *
     * @param rcvpkt Packet
     * @return TRUE if ACK 0
     */
    public boolean hasSeqZero(Packet rcvpkt){
        return rcvpkt.ACK == 0;
    }//hasSeqZero

    /**
     * Retrieves the Payload from the Packet
     * @param packet Packet
     * @return String payload
     */
    public String extract(Packet packet){
        return packet.payload;
    }//extract

    /**
     * Sends data to the Application Layer
     * @param data
     */
    public void deliver_data(String data){
        //send to app layer
    }//deliver_data

    /**
     * Creates a Packet Object
     * @param ACK ACK Field 0 no, 1 yes
     * @param seqNo ACK No 0 or 1
     * @param checksum checksum value for ERROR checking
     * @param data Payload of Packet
     * @return Packet
     */
    public Packet make_pkt(int ACK, int seqNo, long checksum, String data){
        return new Packet(ACK, seqNo, checksum, data);
    }//make_pkt

    /**
     * Sends Packet to the Transport Layer
     * @param sndpkt Packet
     */
    public void udt_send(Packet sndpkt){
        //send to transport layer
    }//udt_send

    /**
     * Create the checksum, set ACK fields and send Packet via UDT
     * @param data Payload of the Packet
     */
    public void rdt_send(String data){
        ad.update(data.getBytes());
        long checksum = ad.getValue();
        Packet sndpkt = make_pkt(0,0,checksum,data);
        udt_send(sndpkt);
    }//rdt_send
}//RDT
