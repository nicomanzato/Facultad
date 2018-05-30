import java.awt.EventQueue;

import com.sun.net.httpserver.*;

import MockPackage.MockGenerator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.util.Date;
import java.util.Observer;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.DaoFactory;
import dao.EtiquetaDao;
import dao.PacienteDao;
import entidades.Categoria;
import entidades.Contenido;
import entidades.Contexto;
import entidades.Etiqueta;
import entidades.Notificacion;
import entidades.Paciente;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Monitor {

	private JFrame frame;
	private JTextField filtroFechaInicioTextField;
	private JTextField filtroFechaFinTextField;
	private JTextField crearEtiquetaTextField;
	private JTextField renombrarEtiquetaTextField;
	private JTableObserver table;
	private JLabel notificacionesNuevasLabel;

	/**
	 * Launch the application prueba.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monitor window = new Monitor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Monitor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1078, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel filtrosPanel = new JPanel();
		filtrosPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		filtrosPanel.setBounds(6, 6, 491, 316);
		frame.getContentPane().add(filtrosPanel);
		filtrosPanel.setLayout(null);
		
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblContenido.setBounds(30, 30, 90, 25);
		filtrosPanel.add(lblContenido);
		
		JComboBoxContenidoObserver<Contenido> filtroContenidoComboBox = new JComboBoxContenidoObserver<Contenido>();
		DaoFactory.getContenidoDao().addObserver(filtroContenidoComboBox);
		filtroContenidoComboBox.setModel(new DefaultComboBoxModel(DaoFactory.getContenidoDao().getContenidos().toArray()));
		filtroContenidoComboBox.setBounds(180, 30, 270, 25);
		filtrosPanel.add(filtroContenidoComboBox);
		
		JLabel lblContexto = new JLabel("Contexto");
		lblContexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblContexto.setBounds(30, 60, 90, 25);
		filtrosPanel.add(lblContexto);
		
		JComboBoxContextoObserver<Contexto> filtroContextoComboBox = new JComboBoxContextoObserver<Contexto>();
		DaoFactory.getContextoDao().addObserver(filtroContextoComboBox);
		filtroContextoComboBox.setModel(new DefaultComboBoxModel(DaoFactory.getContextoDao().getContextos().toArray()));
		filtroContextoComboBox.setBounds(180, 60, 270, 25);
		filtrosPanel.add(filtroContextoComboBox);
		
		JComboBoxPacienteObserver<Paciente> filtroPacienteComboBox = new JComboBoxPacienteObserver<Paciente>();
		filtroPacienteComboBox.setBounds(180, 120, 270, 25);
		DaoFactory.getPacienteDao().addObserver(filtroPacienteComboBox);
		filtroPacienteComboBox.setModel(new DefaultComboBoxModel(DaoFactory.getPacienteDao().getPacientes().toArray()));
		filtrosPanel.add(filtroPacienteComboBox);
		
		JLabel lblNewLabel = new JLabel("Niñ@");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(30, 120, 90, 16);
		filtrosPanel.add(lblNewLabel);
		
		JLabel lblFechahora = new JLabel("Fecha/Hora");
		lblFechahora.setBounds(30, 148, 87, 16);
		filtrosPanel.add(lblFechahora);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(155, 158, 61, 16);
		filtrosPanel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(343, 158, 61, 16);
		filtrosPanel.add(lblHasta);
		
		filtroFechaInicioTextField = new JTextField();
		filtroFechaInicioTextField.setBounds(114, 186, 150, 30);
		filtrosPanel.add(filtroFechaInicioTextField);
		filtroFechaInicioTextField.setColumns(10);
		
		filtroFechaFinTextField = new JTextField();
		filtroFechaFinTextField.setBounds(300, 186, 150, 30);
		filtrosPanel.add(filtroFechaFinTextField);
		filtroFechaFinTextField.setColumns(10);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta");
		lblEtiqueta.setBounds(42, 228, 61, 16);
		filtrosPanel.add(lblEtiqueta);
		
		JComboBoxEtiquetaObserver<Etiqueta> filtroEtiquetaComboBox = new JComboBoxEtiquetaObserver<Etiqueta>();
		DaoFactory.getEtiquetaDao().addObserver(filtroEtiquetaComboBox);
		filtroEtiquetaComboBox.setModel(new DefaultComboBoxModel(DaoFactory.getEtiquetaDao().getEtiquetas().toArray()));
		filtroEtiquetaComboBox.setBounds(169, 224, 270, 27);
		filtrosPanel.add(filtroEtiquetaComboBox);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setBounds(30, 90, 90, 25);
		filtrosPanel.add(lblCategoria);
		
		JComboBoxCategoriaObservable<Categoria> filtroCategoriaComboBox = new JComboBoxCategoriaObservable<Categoria>();
		DaoFactory.getCategoriaDao().addObserver(filtroCategoriaComboBox);
		filtroCategoriaComboBox.setModel(new DefaultComboBoxModel(DaoFactory.getCategoriaDao().getCategorias().toArray()));
		filtroCategoriaComboBox.setBounds(180, 90, 270, 25);
		filtrosPanel.add(filtroCategoriaComboBox);
		
		JButton mostrarTodoButton = new JButton("Mostrar Todo");
		mostrarTodoButton.setEnabled(false);
		mostrarTodoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mostrarTodoButton.setEnabled(false);
				table.reanudarActualizacionesDeNotificaciones();
				table.construirModeloDeNotificaciones();
				notificacionesNuevasLabel.setText("");
				
			}
		});
		mostrarTodoButton.setBounds(30, 270, 190, 29);
		filtrosPanel.add(mostrarTodoButton);
		
		JButton filtrarButton = new JButton("Filtrar!");
		filtrarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				mostrarTodoButton.setEnabled(true);
				table.detenerActualizacionesDeNotificaciones();
				
				Contenido contenido = null;
				Contexto contexto = null;
				Categoria categoria = null;
				Paciente paciente = null;
				String fechaInicio = null;
				String fechaFin = null;
				Etiqueta etiqueta = null;
				
				if (filtroContenidoComboBox.getSelectedItem() != "Seleccionar Contenido"){ contenido = DaoFactory.getContenidoDao().getByNombre(filtroContenidoComboBox.getSelectedItem().toString()); }
				if (filtroContextoComboBox.getSelectedItem() != "Seleccionar Contexto"){ contexto = DaoFactory.getContextoDao().getByNombre(filtroContextoComboBox.getSelectedItem().toString()); }
				if (filtroCategoriaComboBox.getSelectedItem() != "Seleccionar Categoria"){ categoria = DaoFactory.getCategoriaDao().getByNombre(filtroCategoriaComboBox.getSelectedItem().toString()); }
				if (filtroPacienteComboBox.getSelectedItem() != "Seleccionar Paciente"){ paciente = DaoFactory.getPacienteDao().getByNombre(filtroPacienteComboBox.getSelectedItem().toString()); }
				fechaInicio = filtroFechaInicioTextField.getText();
				fechaFin = filtroFechaFinTextField.getText(); 
				if (filtroEtiquetaComboBox.getSelectedItem() != "Seleccionar Etiqueta"){ etiqueta = DaoFactory.getEtiquetaDao().getByNombre(filtroEtiquetaComboBox.getSelectedItem().toString()); }
				
				
				try {
					
					table.construirModeloDeNotificaciones(DaoFactory.getNotificacionDao().filtrar(contenido,contexto,categoria,paciente,fechaInicio,fechaFin,etiqueta));
				
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		filtrarButton.setBounds(232, 270, 218, 29);
		filtrosPanel.add(filtrarButton);
		
		JLabel lblDdmmyyyyHhmm = new JLabel("dd/MM/yyyy hh:mm");
		lblDdmmyyyyHhmm.setBounds(6, 172, 150, 16);
		filtrosPanel.add(lblDdmmyyyyHhmm);

		
		JPanel etiquetasPanel = new JPanel();
		etiquetasPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Etiquetas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		etiquetasPanel.setBounds(509, 6, 545, 316);
		frame.getContentPane().add(etiquetasPanel);
		etiquetasPanel.setLayout(null);
		
		JLabel lblCrearEtiquera = new JLabel("Crear Etiquera");
		lblCrearEtiquera.setBounds(31, 48, 88, 16);
		etiquetasPanel.add(lblCrearEtiquera);
		
		crearEtiquetaTextField = new JTextField();
		crearEtiquetaTextField.setBounds(174, 43, 182, 26);
		etiquetasPanel.add(crearEtiquetaTextField);
		crearEtiquetaTextField.setColumns(10);
		
		JButton crearEtiquetaButton = new JButton("Crear");
		crearEtiquetaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DaoFactory.getEtiquetaDao().insertarEtiqueta(crearEtiquetaTextField.getText());
				crearEtiquetaTextField.setText(null);
				JOptionPane.showMessageDialog(frame,"Etiqueta agregada con exito!");
				
			}
		});
		crearEtiquetaButton.setBounds(403, 43, 117, 29);
		etiquetasPanel.add(crearEtiquetaButton);
		
		JLabel lblEliminarEtiqueta = new JLabel("Eliminar Etiqueta");
		lblEliminarEtiqueta.setBounds(23, 92, 117, 16);
		etiquetasPanel.add(lblEliminarEtiqueta);
		
		JComboBoxEtiquetaObserver<Etiqueta> eliminarEtiquetaComboBox = new JComboBoxEtiquetaObserver<Etiqueta>();
		eliminarEtiquetaComboBox.setModel(new DefaultComboBoxModel(DaoFactory.getEtiquetaDao().getEtiquetas().toArray()));
		DaoFactory.getEtiquetaDao().addObserver(eliminarEtiquetaComboBox);
		eliminarEtiquetaComboBox.setBounds(174, 88, 182, 27);
		etiquetasPanel.add(eliminarEtiquetaComboBox);
		
		JButton eliminarEtiquetaButton = new JButton("Eliminar");
		eliminarEtiquetaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String etiqueta = eliminarEtiquetaComboBox.getSelectedItem().toString();
				
				DaoFactory.getEtiquetaDao().eliminarEtiqueta(etiqueta);
				JOptionPane.showMessageDialog(frame,"La etiqueta "+ etiqueta +" ha sido eliminada");
				
			}
		});
		eliminarEtiquetaButton.setBounds(403, 84, 117, 29);
		etiquetasPanel.add(eliminarEtiquetaButton);
		
		JLabel lblAsignarEtiqueta = new JLabel("Asignar Etiqueta");
		lblAsignarEtiqueta.setBounds(31, 138, 109, 16);
		etiquetasPanel.add(lblAsignarEtiqueta);
		
		JComboBoxEtiquetaObserver<Etiqueta> asignarEtiquetaComboBox = new JComboBoxEtiquetaObserver<Etiqueta>();
		asignarEtiquetaComboBox.setBounds(174, 134, 182, 27);
		DaoFactory.getEtiquetaDao().addObserver(asignarEtiquetaComboBox);
		asignarEtiquetaComboBox.setModel(new DefaultComboBoxModel(DaoFactory.getEtiquetaDao().getEtiquetas().toArray()));
		etiquetasPanel.add(asignarEtiquetaComboBox);
		
		JButton asignarEtiquetaButton = new JButton("Asignar / Desasignar");
		asignarEtiquetaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String etiqueta = asignarEtiquetaComboBox.getSelectedItem().toString();
				String idNotificacion = table.getModel().getValueAt(table.getSelectedRow(), 6).toString();
				
				if ( etiqueta == "Seleccionar Etiqueta"){ 
					
					JOptionPane.showMessageDialog(frame,"Seleccione una Etiqueta");
					
				}else{
					
					if ( idNotificacion == "-1" ){
						
						JOptionPane.showMessageDialog(frame,"Seleccione una notificacion");
						
					}else{
					
						DaoFactory.getNotificacionEtiquetaDao().asignarEtiqueta(DaoFactory.getEtiquetaDao().getByNombre(etiqueta), idNotificacion);
					
						JOptionPane.showMessageDialog(frame,"Se ha asignado la etiqueta");
					}
				}
				
				
				
			}
		});
		asignarEtiquetaButton.setBounds(395, 133, 125, 29);
		etiquetasPanel.add(asignarEtiquetaButton);
		
		JLabel lblRenombrarEtiqueta = new JLabel("Renombrar Etiqueta");
		lblRenombrarEtiqueta.setBounds(23, 182, 130, 16);
		etiquetasPanel.add(lblRenombrarEtiqueta);
		
		JComboBoxEtiquetaObserver<Etiqueta> renombrarEtiquetaComboBox = new JComboBoxEtiquetaObserver<Etiqueta>();
		renombrarEtiquetaComboBox.setBounds(174, 178, 182, 27);
		DaoFactory.getEtiquetaDao().addObserver(renombrarEtiquetaComboBox);
		renombrarEtiquetaComboBox.setModel(new DefaultComboBoxModel(DaoFactory.getEtiquetaDao().getEtiquetas().toArray()));
		etiquetasPanel.add(renombrarEtiquetaComboBox);
		
		JLabel lblNuevoNombre = new JLabel("Nuevo nombre");
		lblNuevoNombre.setBounds(31, 229, 109, 16);
		etiquetasPanel.add(lblNuevoNombre);
		
		renombrarEtiquetaTextField = new JTextField();
		renombrarEtiquetaTextField.setBounds(174, 224, 182, 26);
		etiquetasPanel.add(renombrarEtiquetaTextField);
		renombrarEtiquetaTextField.setColumns(10);
		
		JButton renombrarEtiquetaButton = new JButton("Renombrar");
		renombrarEtiquetaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String nombreAntiguo = renombrarEtiquetaComboBox.getSelectedItem().toString();
				String nombreNuevo = renombrarEtiquetaTextField.getText();
				
				DaoFactory.getEtiquetaDao().renombrarEtiqueta(nombreAntiguo,nombreNuevo);
				
				renombrarEtiquetaTextField.setText(null);
				JOptionPane.showMessageDialog(frame,"Se ha renombrado la etiqueta "+ nombreAntiguo +" a "+ nombreNuevo);
			}
		});
		
		renombrarEtiquetaButton.setBounds(403, 224, 117, 29);
		etiquetasPanel.add(renombrarEtiquetaButton);
		
		notificacionesNuevasLabel = new JLabel("");
		notificacionesNuevasLabel.setForeground(Color.RED);
		notificacionesNuevasLabel.setBounds(31, 277, 489, 16);
		etiquetasPanel.add(notificacionesNuevasLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 335, 1038, 356);
		frame.getContentPane().add(scrollPane);
		
		table = new JTableObserver(notificacionesNuevasLabel);
		
		table.inicializarTablaNotificaciones();
		
		table.getColumnModel().getColumn(0).setPreferredWidth(134);
		scrollPane.setViewportView(table);
		
		
		// SERVIDOR DE NOTIFICACIONES
		
		
		new Thread(new Servidor()).start();
		
	}
}