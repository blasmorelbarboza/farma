DELIMITER $$

CREATE
    PROCEDURE `bmfarmaposventa`.`PedidoInternoDetalle_Ins`(
							  p_id INT ,
							  p_Cantidad INT,
							  p_Secuencia ,
							  p_PedidoCabecera_id,
							  p_Usuario,
							  p_Observacion,
							  p_Producto_id,
							  )

    BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
	 BEGIN
		SHOW ERRORS ;
		/*RESIGNAL;*/
		ROLLBACK;
	 END;	
	DECLARE EXIT HANDLER FOR SQLWARNING
	 BEGIN
		 SHOW WARNINGS ;
		 /*RESIGNAL;*/
		 ROLLBACK;
	 END;     
        START TRANSACTION;    
         p_TipoImpuesto_id,
	 p_DivisionLaboratorio_id,
	 p_Laboratorio_id,
	 p_Presentacion_id
        
		INSERT INTO pedidodetalle (
		  id,
		  Cantidad,
		  Secuencia,
		  PedidoCabecera_id,
		  Usuario,
		  
		  Observacion,
		  Producto_id,
		  TipoImpuesto_id,
		  DivisionLaboratorio_id,
		  Laboratorio_id,
		  Presentacion_id
		) 
		VALUES
		  (
		    'id',
		    'Cantidad',
		    'Secuencia',
		    'PedidoCabecera_id',
		    'Usuario',
		    
		    'Observacion',
		    'Producto_id',
		    'TipoImpuesto_id',
		    'DivisionLaboratorio_id',
		    'Laboratorio_id',
		    'Presentacion_id'
		  ) ;

        
        
        
        COMMIT; 
    END$$

DELIMITER ;