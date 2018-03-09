package fr.ensim.dp.cache;

import fr.ensim.dp.chainOfResponsability.AbstractChainCache;
import fr.ensim.dp.chainOfResponsability.IFilterCache;

/**
 * @author Denis Apparicio
 * 
 */
public interface ICache {

  /**
   * @return Restitue la taille du cache
   */
  long size();

  /**
   * Ajoute un tableau de byte dans le cache.
   * 
   * @param key
   *          clé du buffer à mettre en cache.
   * @param buf
   *          le buffer à mettre en cache.
   * @return <code>true</code> si la mise en cache a réussi,
   *         <code>false</code> sinon.
   */
  boolean add(String key, byte[] buf);

  /**
   * Restitue le buffer en cache.
   * 
   * @param key
   *          clé du buffer recherch&eacutee;
   * @return le buffer en cache ou <code>null</code> si pas de cache
   *         trouvé pour cette clé
   */
  byte[] retrieve(String key);

  /**
   * Efface le cache.
   */
  void clear();

  boolean add(String key, byte[] buff, AbstractChainCache action);

  byte[] retrieve(String key, AbstractChainCache action);

}
