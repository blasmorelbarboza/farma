
package DAO;

import DTO.PedidosDto;
import GENERICOS.MIError;

public interface PedidosDao {
    public boolean agregar(PedidosDto dto) throws MIError;
    
    public boolean anular(Integer idDto) throws MIError;
    
    
}
