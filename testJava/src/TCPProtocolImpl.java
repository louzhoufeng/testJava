import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * TCPProtocol��ʵ����
 * 
 * @date    2010-2-3
 * @time    ����08:58:59
 * @version 1.00
 */
public class TCPProtocolImpl implements TCPProtocol{
  private int bufferSize;
  
  public TCPProtocolImpl(int bufferSize){
    this.bufferSize=bufferSize;
  }

  public void handleAccept(SelectionKey key) throws IOException {
    SocketChannel clientChannel=((ServerSocketChannel)key.channel()).accept();
    clientChannel.configureBlocking(false);
    clientChannel.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocate(bufferSize));
  }

  public void handleRead(SelectionKey key) throws IOException {
    // �����ͻ���ͨ�ŵ��ŵ�
    SocketChannel clientChannel=(SocketChannel)key.channel();
    
    // �õ�����ջ�����
    ByteBuffer buffer=(ByteBuffer)key.attachment();
    buffer.clear();
    
    // ��ȡ��Ϣ��ö�ȡ���ֽ���
    long bytesRead=clientChannel.read(buffer);
    
    if(bytesRead==-1){
      // û�ж�ȡ�����ݵ����
      clientChannel.close();
    }
    else{
      // ��������׼��Ϊ���ݴ���״̬
      buffer.flip();
      
      // ���ֽ�ת��ΪΪUTF-16���ַ���   
      String receivedString=Charset.forName("UTF-16").newDecoder().decode(buffer).toString();
      
      // ����̨��ӡ����
      System.out.println("���յ�����"+clientChannel.socket().getRemoteSocketAddress()+"����Ϣ:"+receivedString);
      
      // ׼�����͵��ı�
      String sendString="���,�ͻ���. @"+new Date().toString()+"���Ѿ��յ������Ϣ"+receivedString;
      buffer=ByteBuffer.wrap(sendString.getBytes("UTF-16"));
      clientChannel.write(buffer);
      
      // ����Ϊ��һ�ζ�ȡ����д����׼��
      key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }
  }

  public void handleWrite(SelectionKey key) throws IOException {
    // do nothing
  }
}