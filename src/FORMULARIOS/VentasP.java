package FORMULARIOS;

import genericos.FormatoVentanas;

import CONTROL.VentasCtrol;
import GENERICOS.Buscador;
import GENERICOS.MIError;

import GENERICOS.setearObjeto;
import java.awt.Color;

import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class VentasP extends javax.swing.JDialog {

    private VentasCtrol ctrolVentas;
    private FormatoVentanas ventanaCabecera;

    public VentasP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();

            ventanaCabecera = new FormatoVentanas(this, FormatoVentanas.tipoVentana.maestro_detalle);
            ctrolVentas = new VentasCtrol(this);
            ctrolVentas.initControlers();
            
        } catch (MIError ex) {
            Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtCodigo.setDocument(new GENERICOS.SoloNumeros());
        txtPrecioPublico.setDocument(new GENERICOS.SoloNumeros());
        txtPorcentaje.setDocument(new GENERICOS.SoloNumeros());
        txtDescuentoMonto.setDocument(new GENERICOS.SoloNumeros());
        txtCantidad.setDocument(new GENERICOS.SoloNumeros());
        txtSubtotal.setDocument(new GENERICOS.SoloNumeros());
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opgrCodicionVenta = new javax.swing.ButtonGroup();
        pnlDetalle = new javax.swing.JPanel();
        pnlVentasDetalle1 = new javax.swing.JPanel();
        lblProducto = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblPrecioPub = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grlProductos = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDescuentoMonto = new javax.swing.JTextField();
        lblDescuento = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        txtPrecioPublico = new javax.swing.JTextField();
        txtIva5 = new javax.swing.JTextField();
        txtIva10 = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnRemover = new javax.swing.JButton();
        pnlBotones = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        pnlCabecera = new javax.swing.JPanel();
        lblRuc = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        txtSucursal = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        lblNombreVendedor = new javax.swing.JLabel();
        lblCondicionVenta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        txtIdVenta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboVendedor = new javax.swing.JComboBox<>();
        opcContado = new javax.swing.JRadioButton();
        opcCredito = new javax.swing.JRadioButton();
        txtfFecha = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlVentasDetalle1.setBorder(javax.swing.BorderFactory.createTitledBorder("DETALLE"));
        pnlVentasDetalle1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pnlVentasDetalle1KeyTyped(evt);
            }
        });

        lblProducto.setText("PRODUCTO");

        lblCodigo.setText("CODIGO");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoActionPerformed(evt);
            }
        });
        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoKeyReleased(evt);
            }
        });

        lblCantidad.setText("CANT.");

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        lblPrecioPub.setText("PRECIO PUB.");

        grlProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(grlProductos);

        jLabel18.setText("TOTAL");

        jLabel19.setText("IVA10%");

        jLabel20.setText("IVA5%");

        txtDescuentoMonto.setText("Desc.xMonto");
        txtDescuentoMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoMontoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoMontoKeyTyped(evt);
            }
        });

        lblDescuento.setText("DESCUENTO");

        txtPorcentaje.setText("%");
        txtPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyTyped(evt);
            }
        });

        txtSubtotal.setText("SubTotal");
        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalActionPerformed(evt);
            }
        });

        jLabel22.setText("SUBTOTAL");

        btnAgregar.setText("+AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtPrecioPublico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioPublicoActionPerformed(evt);
            }
        });
        txtPrecioPublico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioPublicoKeyTyped(evt);
            }
        });

        txtIva5.setText("0");

        txtIva10.setText("0");

        txtTotal.setText("0");

        btnRemover.setText("-REMOVER");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlVentasDetalle1Layout = new javax.swing.GroupLayout(pnlVentasDetalle1);
        pnlVentasDetalle1.setLayout(pnlVentasDetalle1Layout);
        pnlVentasDetalle1Layout.setHorizontalGroup(
            pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVentasDetalle1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVentasDetalle1Layout.createSequentialGroup()
                        .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(lblCodigo)
                            .addComponent(txtCodigo))
                        .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblPrecioPub, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecioPublico, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(lblDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDescuentoMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(txtProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIva5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIva10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlVentasDetalle1Layout.setVerticalGroup(
            pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(10, 10, 10)
                        .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar)))
                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                        .addComponent(lblDescuento)
                        .addGap(11, 11, 11)
                        .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescuentoMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                        .addComponent(lblPrecioPub)
                        .addGap(9, 9, 9)
                        .addComponent(txtPrecioPublico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addGap(11, 11, 11)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                        .addComponent(lblCantidad)
                        .addGap(11, 11, 11)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemover)))
                    .addGroup(pnlVentasDetalle1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblProducto)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlVentasDetalle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtIva5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtIva10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)))
        );

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

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAnular.setText("ANULAR");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir)
                    .addComponent(btnCancelar)
                    .addComponent(btnAnular))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVentasDetalle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(pnlVentasDetalle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlCabecera.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("VENTAS"), "VENTAS"));

        lblRuc.setText("R.U.C. / C.I.Nº");

        txtRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucActionPerformed(evt);
            }
        });
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucKeyPressed(evt);
            }
        });

        txtRazonSocial.setText("Nombre o Razon Social");
        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyReleased(evt);
            }
        });

        txtSucursal.setText("001");

        lblFecha.setText("FECHA");

        lblNombreVendedor.setText("VENDEDOR");

        lblCondicionVenta.setText("CONDICION VENTA");

        jLabel1.setText("NOMBRE / RAZON SOCIAL");

        lblSucursal.setText("SUC.");

        jLabel3.setText("Venta ID");

        cboVendedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVendedorItemStateChanged(evt);
            }
        });
        cboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVendedorActionPerformed(evt);
            }
        });

        opgrCodicionVenta.add(opcContado);
        opcContado.setText("CONTADO");
        opcContado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcContadoActionPerformed(evt);
            }
        });

        opgrCodicionVenta.add(opcCredito);
        opcCredito.setText("CREDITO");

        txtfFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfFechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(lblSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(168, 168, 168)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdVenta))
                        .addGap(112, 112, 112)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                .addComponent(lblCondicionVenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                                .addComponent(lblNombreVendedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                .addComponent(opcContado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(opcCredito))))
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addComponent(lblRuc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSucursal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblNombreVendedor)
                        .addComponent(cboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCondicionVenta))
                    .addComponent(lblFecha, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(12, 12, 12)
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcContado)
                    .addComponent(opcCredito)
                    .addComponent(txtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRuc, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            ctrolVentas.HabilitarBotones(VentasCtrol.estados.inactivo);
            ctrolVentas.HabilitarTexto(VentasCtrol.estados.activo, VentasCtrol.paneles.todos);
            ctrolVentas.limpiarTexto(VentasCtrol.paneles.todos);
            ctrolVentas.idVentas();
        } catch (MIError ex) {
            Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
            ctrolVentas.HabilitarBotones(VentasCtrol.estados.activo);
            ctrolVentas.HabilitarTexto(VentasCtrol.estados.inactivo, VentasCtrol.paneles.todos);
            ctrolVentas.limpiarGrilla();
        try {
            ctrolVentas.limpiarTexto(VentasCtrol.paneles.todos);
        } catch (MIError ex) {
            Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtPrecioPublicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioPublicoActionPerformed

    }//GEN-LAST:event_txtPrecioPublicoActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                //            objetoPedidos.recuperarDescripcion(txtDescriProducto, txtPrecioUnit);
                ctrolVentas.recuperarDatosProductos(txtCodigo, txtProducto, txtPrecioPublico, txtDescuentoMonto, 1);
                txtCantidad.requestFocus();
            } catch (MIError ex) {
                Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoActionPerformed
        try {
            ctrolVentas.recuperarDatosProductos(txtCodigo, txtProducto, txtPrecioPublico, txtDescuentoMonto, 2);
            txtCantidad.requestFocus();
        } catch (MIError ex) {
            Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtProductoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        ctrolVentas.validarTextos();
        try {
            ctrolVentas.agregarNuevaFila();
            ctrolVentas.limpiarTexto(VentasCtrol.paneles.detalle);
        } catch (MIError ex) {
            Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
        }
        // ctrolVentas.agregarFilaDefault();
        btnRemover.setEnabled(true);
        btnGuardar.setEnabled(true);

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        ctrolVentas.eliminarFila();

    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int mensaje = JOptionPane.showConfirmDialog(this, "Desea Registrar Venta?", "ATENCION", JOptionPane.YES_NO_OPTION);
        if (mensaje == JOptionPane.YES_OPTION) {
            try {
                ctrolVentas.altaCabecera();
                ctrolVentas.altaDetalleSP();
            } catch (MIError ex) {
                 Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            btnCancelar.doClick();
        }
        btnCancelar.doClick();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped

        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucActionPerformed


    }//GEN-LAST:event_txtRucActionPerformed

    private void txtRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            setearObjeto objeto = new setearObjeto();
            new Buscador(this, true, ctrolVentas.resulseTabla(VentasCtrol.tablas.clientes), new int[]{0, 1}, objeto).setVisible(true);
            txtRuc.setText(objeto.getCodigo().toString());
            if (objeto.getCodigo() != null) {
                txtRuc.setText(objeto.getCodigo().toString());
                ctrolVentas.recupararDatos(VentasCtrol.tablas.clientes, txtRazonSocial);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ctrolVentas.recupararDatos(VentasCtrol.tablas.clientes, txtRazonSocial);
        }
    }//GEN-LAST:event_txtRucKeyPressed

    private void cboVendedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboVendedorItemStateChanged

    }//GEN-LAST:event_cboVendedorItemStateChanged

    private void cboVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVendedorActionPerformed

    }//GEN-LAST:event_cboVendedorActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        if (txtCantidad.getText().isEmpty() || Integer.parseInt(txtCantidad.getText()) <= 0 || Integer.parseInt(txtCodigo.getText()) <= 0) {
            txtCantidad.setText("0");
            btnAgregar.setEnabled(false);
        } else {
            btnAgregar.setEnabled(true);
        }
        ctrolVentas.subTotal();
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed


    }//GEN-LAST:event_btnAnularActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (txtCodigo.getText().isEmpty()) {
            try {
                ctrolVentas.limpiarTexto(VentasCtrol.paneles.detalle);
            } catch (MIError ex) {
                Logger.getLogger(VentasP.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void pnlVentasDetalle1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlVentasDetalle1KeyTyped

    }//GEN-LAST:event_pnlVentasDetalle1KeyTyped

    private void txtDescuentoMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoMontoKeyTyped
    
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();

        }
//            ctrolVentas.descuentosInt(VentasCtrol.descuentosTipos.porMonto, txtDescuentoMonto, txtPorcentaje);
    }//GEN-LAST:event_txtDescuentoMontoKeyTyped

    private void txtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalActionPerformed

    private void txtPrecioPublicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioPublicoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioPublicoKeyTyped

    private void txtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyReleased
        txtCodigo.setText("0");

    }//GEN-LAST:event_txtProductoKeyReleased

    private void txtPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyReleased
        if (txtPorcentaje.getText().isEmpty()
                || Integer.parseInt(txtPorcentaje.getText()) <= 0) {
            txtPorcentaje.setText("0");

        }
    }//GEN-LAST:event_txtPorcentajeKeyReleased

    private void txtDescuentoMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoMontoKeyReleased
        if (txtDescuentoMonto.getText().isEmpty()
                || Integer.parseInt(txtDescuentoMonto.getText()) <= 0) 
        {
            txtDescuentoMonto.setText("0");

        }
    }//GEN-LAST:event_txtDescuentoMontoKeyReleased

    private void txtPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyTyped
//        ctrolVentas.descuentosInt(VentasCtrol.descuentosTipos.porcentaje, txtDescuentoMonto, txtPorcentaje);
    }//GEN-LAST:event_txtPorcentajeKeyTyped

    private void txtRazonSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyReleased
        ctrolVentas.utiles.convertiraMayusculasEnJtextfield(txtRazonSocial);
    }//GEN-LAST:event_txtRazonSocialKeyReleased

    private void txtfFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfFechaActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void opcContadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcContadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcContadoActionPerformed

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
            java.util.logging.Logger.getLogger(VentasP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentasP dialog = new VentasP(new javax.swing.JFrame(), true);
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
    public javax.swing.JButton btnAnular;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JButton btnRemover;
    public javax.swing.JButton btnSalir;
    public javax.swing.JComboBox<String> cboVendedor;
    public javax.swing.JTable grlProductos;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lblCantidad;
    public javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCondicionVenta;
    public javax.swing.JLabel lblDescuento;
    public javax.swing.JLabel lblFecha;
    public javax.swing.JLabel lblNombreVendedor;
    public javax.swing.JLabel lblPrecioPub;
    public javax.swing.JLabel lblProducto;
    public javax.swing.JLabel lblRuc;
    public javax.swing.JLabel lblSucursal;
    public javax.swing.JRadioButton opcContado;
    public javax.swing.JRadioButton opcCredito;
    private javax.swing.ButtonGroup opgrCodicionVenta;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlVentasDetalle1;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtCodigo;
    public javax.swing.JTextField txtDescuentoMonto;
    public javax.swing.JTextField txtIdVenta;
    public javax.swing.JTextField txtIva10;
    public javax.swing.JTextField txtIva5;
    public javax.swing.JTextField txtPorcentaje;
    public javax.swing.JTextField txtPrecioPublico;
    public javax.swing.JTextField txtProducto;
    public javax.swing.JTextField txtRazonSocial;
    public javax.swing.JTextField txtRuc;
    public javax.swing.JTextField txtSubtotal;
    public javax.swing.JTextField txtSucursal;
    public javax.swing.JTextField txtTotal;
    public javax.swing.JFormattedTextField txtfFecha;
    // End of variables declaration//GEN-END:variables
}
