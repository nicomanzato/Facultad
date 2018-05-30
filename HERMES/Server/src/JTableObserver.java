import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.DaoFactory;
import entidades.Notificacion;

public class JTableObserver extends JTable implements Observer{

	boolean actualizarTabla = true;
	JLabel labelNotificacion;
	int cantNotificaciones = 0;
	
	public boolean isActualizarTabla() {
		return actualizarTabla;
	}

	public void setActualizarTabla(boolean actualizarTabla) {
		this.actualizarTabla = actualizarTabla;
	}

	public JLabel getLabelNotificacion() {
		return labelNotificacion;
	}

	public void setLabelNotificacion(JLabel labelNotificacion) {
		this.labelNotificacion = labelNotificacion;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (actualizarTabla) {
			
			this.construirModeloDeNotificaciones();
			
		}else{
			
			if (o.getClass().getName() == "dao.NotificacionDao"){
				
				cantNotificaciones++;
				
				labelNotificacion.setText("Llegaron "+ cantNotificaciones +" notificaciones nuevas");
				
			}else{
	
				this.construirModeloDeNotificaciones(DaoFactory.getNotificacionDao().getUpdatedNotificaciones((DefaultTableModel)this.getModel()));
				
			}
			
		}
		
	}
	
	public JTableObserver(JLabel label){
		
		this.setLabelNotificacion(label);
		
	}
	
	public void inicializarTablaNotificaciones(){
		
		this.agregarObservables();
		
		this.construirModeloDeNotificaciones();
	}
	
	public void construirModeloDeNotificaciones(){
		
		DefaultTableModel tablaModelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Fecha/Hora envio", "Contenido", "Contexto", "Categoria", "Nin@", "Etiquetas", "id"
				}
			);
		
		this.setModel(tablaModelo);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaModelo);
		
		this.setRowSorter(sorter);
		
		this.removeColumn(this.getColumnModel().getColumn(6));
		
		DaoFactory.getNotificacionDao().addToModel(DaoFactory.getNotificacionDao().getNotificaciones(), this.getModel());
		
		
	}
	
	public void construirModeloDeNotificaciones(List<Notificacion> notificaciones){
		
		DefaultTableModel tablaModelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Fecha/Hora envio", "Contenido", "Contexto", "Categoria", "Nin@", "Etiquetas", "id"
				}
			);
		
		this.setModel(tablaModelo);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaModelo);
		
		this.setRowSorter(sorter);
		
		this.removeColumn(this.getColumnModel().getColumn(6));
		
		DaoFactory.getNotificacionDao().addToModel(notificaciones, this.getModel());
		
	}
	
	public void agregarObservables(){
		
		DaoFactory.getCategoriaDao().addObserver(this);
		DaoFactory.getPacienteDao().addObserver(this);
		DaoFactory.getContextoDao().addObserver(this);
		DaoFactory.getContenidoDao().addObserver(this);
		DaoFactory.getEtiquetaDao().addObserver(this);
		DaoFactory.getNotificacionDao().addObserver(this);
		DaoFactory.getNotificacionEtiquetaDao().addObserver(this);
		
	}
	
	public void detenerActualizacionesDeNotificaciones(){
		
		actualizarTabla = false;
		
	}
	
	public void reanudarActualizacionesDeNotificaciones(){
		
		actualizarTabla = true;
		
		cantNotificaciones = 0;
		
	}

	public void quitarObservables(){
		
		DaoFactory.getCategoriaDao().deleteObserver(this);
		DaoFactory.getPacienteDao().deleteObserver(this);
		DaoFactory.getContextoDao().deleteObserver(this);
		DaoFactory.getContenidoDao().deleteObserver(this);
		DaoFactory.getEtiquetaDao().deleteObserver(this);
		DaoFactory.getNotificacionDao().deleteObserver(this);
		DaoFactory.getNotificacionEtiquetaDao().deleteObserver(this);
		
	}

}
