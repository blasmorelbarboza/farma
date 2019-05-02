package FORMULARIOS;

import CONTROL.PedidoInternoCtrol;
import GENERICOS.MIError;
import genericos.FormatoVentanas; 
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PedidoInterno extends javax.swing.JDialog {

    private PedidoInternoCtrol ctrolPedidoInt;
    private FormatoVentanas ventanaCabecera;
    
    public PedidoInterno(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
                                    
            ventanaCabecera = new FormatoVentanas(this, FormatoVentanas.tipoVentana.maestro_detalle);
            ctrolPedidoInt = new PedidoInternoCtrol(this);
            
            ctrolPedidoInt.initControlers();
            btnCancelar.doClick();
            
            txtCodigo.setDocument(new GENERICOS.SoloNumeros());
            txtLaboratorio.setDocument(new GENERICOS.SoloNumeros());
            txtDivision.setDocument(new GENERICOS.SoloNumeros());
            txtCantidad.setDocument(new GENERICOS.SoloNumeros());
            txtPedidoId.setDocument(new GENERICOS.SoloNumeros());
        } catch (MIError ex) {
            Logger.getLogger(PedidoInterno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPedidoInterno = new javax.swing.JPanel();
        pnlBotonera = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        pnlDetallePedido = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grlPedidoDetalle = new javax.swing.JTable();
        pnlDetallePedidoTxt = new javax.swing.JPanel();
        txtProducto = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtLaboratorio = new javax.swing.JTextField();
        lblLaboratorio = new javax.swing.JLabel();
        txtDivision = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblDivision1 = new javax.swing.JLabel();
        txtDivisionNombre = new javax.swing.JTextField();
        txtLaboratorioNombre = new javax.swing.JTextField();
        lblPresentacion = new javax.swing.JLabel();
        txtPresentacion = new javax.swing.JTextField();
        pnlDetallePedidoBtn = new javax.swing.JPanel();
        btnRemover = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        pnlPedidoCabecera = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPedidoId = new javax.swing.JTextField();
        cboEstadoPedido = new javax.swing.JComboBox<>();
        lblEstadoPedido = new javax.swing.JLabel();
        txtfFechaPedido = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlPedidoInterno.setBorder(javax.swing.BorderFactory.createTitledBorder("PEDIDO INTERNO"));

        pnlBotonera.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotoneraLayout = new javax.swing.GroupLayout(pnlBotonera);
        pnlBotonera.setLayout(pnlBotoneraLayout);
        pnlBotoneraLayout.setHorizontalGroup(
            pnlBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlBotoneraLayout.setVerticalGroup(
            pnlBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoneraLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlDetallePedido.setBorder(javax.swing.BorderFactory.createTitledBorder("DETALLE PEDIDO"));

        grlPedidoDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(grlPedidoDetalle);

        txtProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoActionPerformed(evt);
            }
        });

        txtLaboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLaboratorioActionPerformed(evt);
            }
        });

        lblLaboratorio.setText("LABORATORIO");

        txtDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDivisionActionPerformed(evt);
            }
        });

        lblCodigo.setText("CODIGO");

        lblProducto.setText("PRODUCTO");

        lblCantidad.setText("CANTIDAD");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        lblDivision1.setText("DIVISION");

        txtDivisionNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDivisionNombreActionPerformed(evt);
            }
        });

        txtLaboratorioNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLaboratorioNombreActionPerformed(evt);
            }
        });

        lblPresentacion.setText("PRESENTACION");

        txtPresentacion.setText("jTextField1");

        javax.swing.GroupLayout pnlDetallePedidoTxtLayout = new javax.swing.GroupLayout(pnlDetallePedidoTxt);
        pnlDetallePedidoTxt.setLayout(pnlDetallePedidoTxtLayout);
        pnlDetallePedidoTxtLayout.setHorizontalGroup(
            pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePedidoTxtLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtCodigo)
                        .addComponent(lblCodigo))
                    .addComponent(lblProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetallePedidoTxtLayout.createSequentialGroup()
                        .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPresentacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPresentacion, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                    .addGroup(pnlDetallePedidoTxtLayout.createSequentialGroup()
                        .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLaboratorio)
                            .addGroup(pnlDetallePedidoTxtLayout.createSequentialGroup()
                                .addComponent(txtLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLaboratorioNombre)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDetallePedidoTxtLayout.createSequentialGroup()
                                .addComponent(txtDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDivisionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDivision1))))
                .addGap(18, 18, 18)
                .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
        pnlDetallePedidoTxtLayout.setVerticalGroup(
            pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePedidoTxtLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePedidoTxtLayout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePedidoTxtLayout.createSequentialGroup()
                        .addComponent(lblLaboratorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLaboratorioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePedidoTxtLayout.createSequentialGroup()
                        .addComponent(lblCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDivision1)
                        .addGroup(pnlDetallePedidoTxtLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDivisionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(pnlDetallePedidoTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPresentacion)
                    .addComponent(txtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnRemover.setText("- REMOVER");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAgregar.setText("+ AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetallePedidoBtnLayout = new javax.swing.GroupLayout(pnlDetallePedidoBtn);
        pnlDetallePedidoBtn.setLayout(pnlDetallePedidoBtnLayout);
        pnlDetallePedidoBtnLayout.setHorizontalGroup(
            pnlDetallePedidoBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePedidoBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetallePedidoBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlDetallePedidoBtnLayout.setVerticalGroup(
            pnlDetallePedidoBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePedidoBtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout pnlDetallePedidoLayout = new javax.swing.GroupLayout(pnlDetallePedido);
        pnlDetallePedido.setLayout(pnlDetallePedidoLayout);
        pnlDetallePedidoLayout.setHorizontalGroup(
            pnlDetallePedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetallePedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetallePedidoLayout.createSequentialGroup()
                        .addComponent(pnlDetallePedidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(pnlDetallePedidoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        pnlDetallePedidoLayout.setVerticalGroup(
            pnlDetallePedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetallePedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetallePedidoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDetallePedidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
        );

        pnlPedidoCabecera.setBorder(javax.swing.BorderFactory.createTitledBorder("PEDIDO"));

        jLabel1.setText("FECHA");

        jLabel2.setText("Nº PEDIDO");

        cboEstadoPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIENTE", "SOLICITADO", "RECIBIDO", "ANULADO" }));

        lblEstadoPedido.setText("ESTADO");

        txtfFechaPedido.setText("jFormattedTextField1");

        javax.swing.GroupLayout pnlPedidoCabeceraLayout = new javax.swing.GroupLayout(pnlPedidoCabecera);
        pnlPedidoCabecera.setLayout(pnlPedidoCabeceraLayout);
        pnlPedidoCabeceraLayout.setHorizontalGroup(
            pnlPedidoCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoCabeceraLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPedidoId, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstadoPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlPedidoCabeceraLayout.setVerticalGroup(
            pnlPedidoCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPedidoCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPedidoCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtPedidoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPedidoCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblEstadoPedido)
                        .addComponent(cboEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtfFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPedidoInternoLayout = new javax.swing.GroupLayout(pnlPedidoInterno);
        pnlPedidoInterno.setLayout(pnlPedidoInternoLayout);
        pnlPedidoInternoLayout.setHorizontalGroup(
            pnlPedidoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoInternoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(pnlBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlDetallePedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPedidoInternoLayout.createSequentialGroup()
                .addComponent(pnlPedidoCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPedidoInternoLayout.setVerticalGroup(
            pnlPedidoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoInternoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(pnlPedidoCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDetallePedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPedidoInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPedidoInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
        try {
            ctrolPedidoInt.limpiarTexto(PedidoInternoCtrol.paneles.todos);
            ctrolPedidoInt.HabilitarBotones(PedidoInternoCtrol.estados.inactivo);
            ctrolPedidoInt.habilitarTexto( PedidoInternoCtrol.paneles.detalleTxt, PedidoInternoCtrol.estados.activo);
            ctrolPedidoInt.idPedido();

            
            
        } catch (MIError ex) {
            Logger.getLogger(PedidoInterno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
int mensaje = JOptionPane.showConfirmDialog(this, "Desea Registrar Venta?", "ATENCION", JOptionPane.YES_NO_OPTION);
        if (mensaje == JOptionPane.YES_OPTION) {
            try {
                ctrolPedidoInt.altaCabecera();
                ctrolPedidoInt.altaDetalleSP();
            } catch (MIError ex) {
                Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
        }
        btnCancelar.doClick();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
            ctrolPedidoInt.validarTextos();
            btnGuardar.setEnabled(true);          
//        ctrolPedidoInt.agregarFilaDefault();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLaboratorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLaboratorioActionPerformed

    private void txtDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDivisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDivisionActionPerformed

    private void txtProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoActionPerformed
        try {
            ctrolPedidoInt.recuperarDatosProductos(txtCodigo, txtProducto,txtLaboratorio , txtLaboratorioNombre, txtDivision,txtDivisionNombre,txtPresentacion , 2);
            txtCantidad.requestFocus();
            btnAgregar.setEnabled(true);
        } catch (MIError ex) {
            Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtProductoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {

            ctrolPedidoInt.HabilitarBotones(PedidoInternoCtrol.estados.activo);
            ctrolPedidoInt.limpiarTexto(PedidoInternoCtrol.paneles.todos);
                      
            
            ctrolPedidoInt.habilitarTexto( PedidoInternoCtrol.paneles.todos, PedidoInternoCtrol.estados.inactivo);
            ctrolPedidoInt.limpiarGrilla();
            
        
        } catch (MIError ex) {
            Logger.getLogger(PedidoInterno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            ctrolPedidoInt.limpiarTexto(PedidoInternoCtrol.paneles.detalleTxt);
        } catch (MIError ex) {
            Logger.getLogger(PedidoInterno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtDivisionNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDivisionNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDivisionNombreActionPerformed

    private void txtLaboratorioNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLaboratorioNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLaboratorioNombreActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {                               
                ctrolPedidoInt.recuperarDatosProductos(txtCodigo, txtProducto,txtLaboratorio , txtLaboratorioNombre, txtDivision,txtDivisionNombre,txtPresentacion , 1);
                txtCantidad.requestFocus();
                btnAgregar.setEnabled(true);
            } catch (MIError ex) {
                Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        ctrolPedidoInt.eliminarFila();
    }//GEN-LAST:event_btnRemoverActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PedidoInterno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidoInterno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidoInterno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidoInterno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PedidoInterno dialog = new PedidoInterno(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnEditar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JButton btnRemover;
    public javax.swing.JButton btnSalir;
    public javax.swing.JComboBox<String> cboEstadoPedido;
    public javax.swing.JTable grlPedidoDetalle;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblCantidad;
    public javax.swing.JLabel lblCodigo;
    public javax.swing.JLabel lblDivision1;
    public javax.swing.JLabel lblEstadoPedido;
    public javax.swing.JLabel lblLaboratorio;
    public javax.swing.JLabel lblPresentacion;
    public javax.swing.JLabel lblProducto;
    public javax.swing.JPanel pnlBotonera;
    public javax.swing.JPanel pnlDetallePedido;
    public javax.swing.JPanel pnlDetallePedidoBtn;
    public javax.swing.JPanel pnlDetallePedidoTxt;
    public javax.swing.JPanel pnlPedidoCabecera;
    public javax.swing.JPanel pnlPedidoInterno;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtCodigo;
    public javax.swing.JTextField txtDivision;
    public javax.swing.JTextField txtDivisionNombre;
    public javax.swing.JTextField txtLaboratorio;
    public javax.swing.JTextField txtLaboratorioNombre;
    public javax.swing.JTextField txtPedidoId;
    public javax.swing.JTextField txtPresentacion;
    public javax.swing.JTextField txtProducto;
    public javax.swing.JFormattedTextField txtfFechaPedido;
    // End of variables declaration//GEN-END:variables
}
