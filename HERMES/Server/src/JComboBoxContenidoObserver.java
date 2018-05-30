import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dao.DaoFactory;

public class JComboBoxContenidoObserver<Contenido> extends JComboBox implements Observer {
	
	@Override
	public void update(Observable o, Object arg) {
		
		this.setModel(new DefaultComboBoxModel(DaoFactory.getContenidoDao().getContenidos().toArray()));
		
	}

}
