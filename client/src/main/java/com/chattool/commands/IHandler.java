package com.chattool.commands;

/**
 * @author Leboc Philippe.
 * @param <K> The Handler
 * @param <V> The Handler's key
 */
public interface IHandler<K, V>
{
	/**
	 * @param handler The handler to be registered
	 */
	void registerHandler(K handler);

	/**
	 * @param handler The handler to be removed
	 */
	void removeHandler(K handler);

	/**
	 * @param val The given command
	 * @return The handler corresponding to the command key
	 */
	K getHandler(V val);

	/**
	 * @return The registered handler count
	 */
	int size();
}
