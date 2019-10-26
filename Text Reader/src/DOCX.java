import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.StringTokenizer;

public class DOCX extends Conversor {


    static Lista EvaluacionBusq = new Lista();
    Tree arbol = new Tree();
    Conversor recorrida = new Conversor();
    /***
     *
     * @param a texto DOCX que se leerá,
     *          se separará por cada palabra y se intriducirá como una
     *          nueva fruta en el árbol
     */

    public void leer(String a){
        try {
            File file = new File(a);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            XWPFDocument document = new XWPFDocument(fis);
            String sa = "";
            int numTokens = 0;
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                StringTokenizer st = new StringTokenizer(para.getText());

                while (st.hasMoreTokens()){
                    sa = st.nextToken();
                    numTokens++;
                    arbol.Insertar(numTokens, sa.hashCode());
                }



                StringTokenizer leer = new StringTokenizer(Controller.busqueda);
                String su = "";
                while (leer.hasMoreTokens()) {
                    su = leer.nextToken();
                    EvaluacionBusq.addLast(su);
                    System.out.println(su+ "lista");
                }
                Conversor.Evaluacion = EvaluacionBusq;
                Controller.Docu = para.getText();
                recorrida.Recorrer(arbol.raiz, para.getText(), numTokens);

            }

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

