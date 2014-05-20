import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * TCP�������� 
 * 
 * @date    2010-2-3
 * @time    ����08:39:48
 * @version 1.00
 */
public class TCPServer{
  // ��������С
  private static final int BufferSize=1024;
  
  // ��ʱʱ�䣬��λ����
  private static final int TimeOut=3000;
  
  // ���ؼ����˿�
  private static final int ListenPort=1978;
  
  public static void main(String[] args) throws IOException{
    // ����ѡ����
    Selector selector=Selector.open();
    
    // �򿪼����ŵ�
    ServerSocketChannel listenerChannel=ServerSocketChannel.open();
    
    // �뱾�ض˿ڰ�
    listenerChannel.socket().bind(new InetSocketAddress(ListenPort));
    
    // ����Ϊ������ģʽ
    listenerChannel.configureBlocking(false);
    
    // ��ѡ�����󶨵������ŵ�,ֻ�з������ŵ��ſ���ע��ѡ����.����ע�������ָ�����ŵ����Խ���Accept����
    listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
    
    // ����һ������Э���ʵ����,�������������
    TCPProtocol protocol=new TCPProtocolImpl(BufferSize);
    
    // ����ѭ��,�ȴ�IO
    while(true){
      // �ȴ�ĳ�ŵ�����(��ʱ)
      if(selector.select(TimeOut)==0){
        System.out.print("���Եȴ�.");
        continue;
      }
      
      // ȡ�õ�����.selectedKeys()�а�����ÿ��׼����ĳһI/O�������ŵ���SelectionKey
      Iterator<SelectionKey> keyIter=selector.selectedKeys().iterator();
      
      while(keyIter.hasNext()){
        SelectionKey key=keyIter.next();
        
        try{
          if(key.isAcceptable()){
            // �пͻ�����������ʱ
            protocol.handleAccept(key);
          }
          
          if(key.isReadable()){
            // �ӿͻ��˶�ȡ����
            protocol.handleRead(key);
          }
          
          if(key.isValid() && key.isWritable()){
            // �ͻ��˿�дʱ
            protocol.handleWrite(key);
          }
        }
        catch(IOException ex){
          // ����IO�쳣����ͻ��˶Ͽ����ӣ�ʱ�Ƴ�������ļ�
          keyIter.remove();
          continue;
        }
        
        // �Ƴ�������ļ�
        keyIter.remove();
      }
    }
  }
}