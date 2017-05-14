/**
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server;

public interface Card {
	
	public String getName();
	
	public String getDescription();
	
	public boolean useCard(Player player);

}
