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

    /**
     * Constructor of FileChooser.
     * Display a {@link JFileChooser}
     * @param acceptedExtensions 
     */
    protected FileChooser(String... acceptedExtensions)
    {
        this.acceptedExtensions = acceptedExtensions;

        String sAcceptedExtensions = ""; // string to display in the FileChooser file type select list
        for (String extension : this.acceptedExtensions)
        {
            sAcceptedExtensions += "." + extension + ", ";
        }

        sAcceptedExtensions = sAcceptedExtensions.substring(0, sAcceptedExtensions.length() - 2);  // strip trailing ' , '

        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                sAcceptedExtensions, this.acceptedExtensions
        );

        chooser.setFileFilter(filter);

        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            // the file is always available, even if it was invalid.
            // it's the user responsability to verify the validity if needed
            this.selectedFile = chooser.getSelectedFile();

            String[] parts = chooser.getSelectedFile().getPath().split("\\.");
            if (parts.length <= 1)
            {
                this.valid = false;
            } else
            {
                String extension = parts[parts.length - 1];
                if (!Arrays.asList(this.acceptedExtensions).contains(extension))
                {
                    this.valid = false;
                }
            }
        } else if (result == JFileChooser.CANCEL_OPTION)
        {
            this.cancelled = true;
        }
    }

    /**
     * Getter for the file selected by the user.
     * This returns the file even if it does not match the extension(s) given 
     * at class instantiation.  A validity verification is provided at 
     * {@link isValid()}
     * @return The file selected by the user.
     */
    protected File getFile()
    {
        return this.selectedFile;
    }

    /**
     * Checks if the operation was canceled.
     * 
     * @return <code>true</code> if the user clicked the "Cancel" button,
     * <code>false</code> if he clicked the "Save" button
     */
    protected boolean isCancel()
    {
        return this.cancelled;
    }

    /**
     * Checks if the chosen file's extension was in the list given at instantiation.
     * @return 
     */
    protected boolean isValid()
    {
        return this.valid;
    }
}
