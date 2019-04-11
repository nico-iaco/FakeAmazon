package com.iacovelli.fakeamazon.model;

public interface Identity<ID> {

	ID getId();

	<T extends Identity> T setId(ID id);

}
