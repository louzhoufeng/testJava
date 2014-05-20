import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;


/**
 * NIO TCP �ͻ���
 * 
 * @date    2010-2-3
 * @time    ����03:33:26
 * @version 1.00
 */
public class TCPClient{
  // �ŵ�ѡ����
  private Selector selector;
  
  // �������ͨ�ŵ��ŵ�
  SocketChannel socketChannel;
  
  // Ҫ���ӵķ�����Ip��ַ
  private String hostIp;
  
  // Ҫ���ӵ�Զ�̷������ڼ����Ķ˿�
  private int hostListenningPort;
  
  /**
   * ���캯��
   * @param HostIp
   * @param HostListenningPort
   * @throws IOException
   */
  public TCPClient(String HostIp,int HostListenningPort)throws IOException{
    this.hostIp=HostIp;
    this.hostListenningPort=HostListenningPort;   
    
    initialize();
  }
  
  /**
   * ��ʼ��
   * @throws IOException
   */
  private void initialize() throws IOException{
    // �򿪼����ŵ�������Ϊ������ģʽ
    socketChannel=SocketChannel.open(new InetSocketAddress(hostIp, hostListenningPort));
    socketChannel.configureBlocking(false);
    
    // �򿪲�ע��ѡ�������ŵ�
    selector = Selector.open();
    socketChannel.register(selector, SelectionKey.OP_READ);
    
    // ������ȡ�߳�
    new TCPClientReadThread(selector);
  }
  
  /**
   * �����ַ�����������
   * @param message
   * @throws IOException
   */
  public void sendMsg(String message) throws IOException{
    ByteBuffer writeBuffer=ByteBuffer.wrap(message.getBytes("UTF-16"));
    socketChannel.write(writeBuffer);
  }
  
  public static void main(String[] args) throws IOException{
    TCPClient client=new TCPClient("192.168.1.9",10002);
    
    client.sendMsg("���!Nio!�������ƿ���,�λش�����Ӫ");
  }
}