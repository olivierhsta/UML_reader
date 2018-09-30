
package views;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author 1485246
 */
public class FileChooser 
{
	
	private File selectedFile;
	private boolean cancelled = false;
    
    protected FileChooser()
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "UML files", "uml", "ucd");
        chooser.setFileFilter(filter);
        int result = chooser.showSaveDialog(null);
        
		if (result == JFileChooser.APPROVE_OPTION)
		{
			this.selectedFile = chooser.getSelectedFile();
		} else if (result == JFileChooser.CANCEL_OPTION)
		{
			this.cancelled = true;
		}
    }
    
	protected File getFile() 
    {
    	return this.selectedFile;
    }
	
	protected boolean cancelled()
	{
		return this.cancelled;
	}
}