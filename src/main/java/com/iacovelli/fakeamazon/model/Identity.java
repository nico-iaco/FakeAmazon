package com.iacovelli.fakeamazon.model;

/**
 * This interface is used to implement the principle of primary key
 * @param <ID>
 */
public interface Identity<ID> {

	ID getId();

	<T extends Identity> T setId(ID id);

}
