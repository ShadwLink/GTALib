/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdr;

import file_io.ByteReader;
import file_io.Message;

/**
 *
 * @author Kilian
 */
public class ResourceFile {
    private ResourceHeader _header;
    private Compression _codec;

    public ResourceFile(){
    }

    public byte[] Read(ByteReader br, int fileSize){
        _header = new ResourceHeader();

        _header.Read(br);
        
        if(_header.Magic != ResourceHeader.MagicValue){
            Message.displayMsgHigh("Iets mis met rcs file");
        }

        _codec = new Compression();

        switch (_header.CompressCodec)
        {
            case LZX:
                Message.displayMsgHigh("LZX");
                _codec.setCodec(_header.CompressCodec);
                break;
            case Deflate:
                Message.displayMsgHigh("Deflate");
                _codec.setCodec(_header.CompressCodec);
                break;
            default:
                Message.displayMsgHigh("Compressie fail");
        }

        int totalMemSize = _header.GetSystemMemSize() + _header.GetGraphicsMemSize();
        Message.displayMsgHigh("Total Mem Size: " + totalMemSize);
        Message.displayMsgHigh("Max buffer size: " + br.moreToRead());
        Message.displayMsgHigh("File Size: " + fileSize);
        if(fileSize == -1) fileSize = br.moreToRead();

        byte stream[] = _codec.decompress(br.toArray(br.getCurrentOffset(), br.moreToRead()), totalMemSize);

        Message.displayMsgHigh("System Mem Size: " + _header.GetSystemMemSize());
        Message.displayMsgHigh("Graphic Mem Size: " + _header.GetGraphicsMemSize());
        
        return stream;
    }

    public int getGraphicSize(){
        return _header.GetGraphicsMemSize();
    }

    public int getSystemSize(){
        return _header.GetSystemMemSize();
    }

    /*public void Write(byte stream[], String file){
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(file);
            DataOutputStream out = new DataOutputStream(fs);
            _header.Write(out);
            _codec.compress(out, stream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ResourceFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fs.close();
            } catch (IOException ex) {
                Logger.getLogger(ResourceFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/
}
