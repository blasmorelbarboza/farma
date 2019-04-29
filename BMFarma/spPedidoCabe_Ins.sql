DELIMITER $$

USE `bmfarmaposventa`$$

DROP PROCEDURE IF EXISTS `PedidoInterno_Ins`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PedidoInterno_Ins`(p_id INT
						, p_Descripcion VARCHAR(45)
						, p_Estado VARCHAR(20))
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
    
		    INSERT INTO bmfarmaposventa.pedidointerno (
			  id,
			  Descripcion,
			  FechaPedido,
			  Usuario,
			  Estado,
			  Sucursal_id
			) 
			VALUES
			  (
			    p_id,
			    p_Descripcion,
			    CURRENT_DATE,
			    current_user,
			    p_Estado,
			    1
			  ) ;
	COMMIT; 
    END$$

DELIMITER ;