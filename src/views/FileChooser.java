package views;

import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author 1485246
 */
public class FileChooser
{

    private File selectedFile;
    private String[] acceptedExtensions;
    private boolean cancelled = false, valid = true;

    protected FileChooser(String... acceptedExtensions)
    {
        this.acceptedExtensions = acceptedExtensions;
        String sAcceptedExtensions = "";
        for (String extension : this.acceptedExtensions)
        {
            sAcceptedExtensions += "." + extension + ", ";
        }
        sAcceptedExtensions = sAcceptedExtensions.substring(0, sAcceptedExtensions.length() - 2);
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                sAcceptedExtensions, this.acceptedExtensions
        );

        chooser.setFileFilter(filter);
        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            this.selectedFile = chooser.getSelectedFile();

            String[] parts = chooser.getSelectedFile().getPath().split("\\.");
            if (parts.length <= 1)
            {
                this.valid = false;
            } else
            {
                String extension = parts[parts.length - 1];
                if (Arrays.asList(this.acceptedExtensions).contains(extension))
                {
                    // the file is always available, even if it was invalid.
                    // it's the use responsability to verify the validity if needed
                    this.selectedFile = chooser.getSelectedFile();
                } else
                {
                    this.valid = false;
                }
            }
        } else if (result == JFileChooser.CANCEL_OPTION)
        {
            this.cancelled = true;
        }
    }

    protected File getFile()
    {
        return this.selectedFile;
    }

    protected boolean isCancel()
    {
        return this.cancelled;
    }

    protected boolean isValid()
    {
        return this.valid;
    }
}
