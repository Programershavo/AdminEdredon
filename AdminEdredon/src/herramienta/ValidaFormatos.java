
package herramienta;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author lightemp
 */
public class ValidaFormatos {

    public ValidaFormatos() {
    }

    public Boolean isEmpty(JTextField campo) {
        if (campo.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    //metodo para validar correo electronio
    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            //System.out.println("[" + mat.group() + "]");
            return true;
        } else {
            return false;
        }
    }
    //metodo para validar si la fecha es correcta

    public boolean isDate(String fechax) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = formatoFecha.parse(fechax);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isDecimal(JTextField numero) {
        try {
            Double.parseDouble(numero.getText().toString().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isNumeric(JTextField numero) {
        try {
            Integer.parseInt(numero.getText().toString().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean isIPAdress(JTextField ip) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9]{1,3}).([0-9]{1,3}).([0-9]{1,3}).([0-9]{1,3})$");
        mat = pat.matcher(ip.getText());
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        } else {
            return false;
        }
    }
}
