import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * TCPServerSelector���ض�Э���ͨ�ŵĽӿ�
 * 
 * @date    2010-2-3
 * @time    ����08:42:42
 * @version 1.00
 */
public interface TCPProtocol{
  /**
   * ����һ��SocketChannel�Ĵ���
   * @param key
   * @throws IOException
   */
  void handleAccept(SelectionKey key) throws IOException;
  
  /**
   * ��һ��SocketChannel��ȡ��Ϣ�Ĵ���
   * @param key
   * @throws IOException
   */
  void handleRead(SelectionKey key) throws IOException;
  
  /**
   * ��һ��SocketChannelд����Ϣ�Ĵ���
   * @param key
   * @throws IOException
   */
  void handleWrite(SelectionKey key) throws IOException;
}