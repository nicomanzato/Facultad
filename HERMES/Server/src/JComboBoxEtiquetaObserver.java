import java.util.Observable;
import java.util.Observer;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dao.DaoFactory;
import dao.EtiquetaDao;

public class JComboBoxEtiquetaObserver<Etiqueta> extends JComboBox implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		
		this.setModel(new DefaultComboBoxModel(DaoFactory.getEtiquetaDao().getEtiquetas().toArray()));
		
	}

}