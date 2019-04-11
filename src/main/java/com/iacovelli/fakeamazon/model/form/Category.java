package com.iacovelli.fakeamazon.model.form;

import java.util.ArrayList;
import java.util.List;

public enum Category {

	ABBIGLIAMENTO {
		@Override
		public List<String> getSubcategory() {
			List<String> subcategory = new ArrayList<>();
			subcategory.add("Moda uomo");
			subcategory.add("Moda donna");
			subcategory.add("Moda bambini");
			return subcategory;
		}

		@Override
		public String toString() {
			return "abbigliamento";
		}
	},
	ALIMENTARI {
		@Override
		public List<String> getSubcategory() {
			List<String> subcategory = new ArrayList<>();
			subcategory.add("Carne");
			subcategory.add("Pesce");
			subcategory.add("Dolci");
			return subcategory;
		}

		@Override
		public String toString() {
			return "alimentari";
		}
	},
	GIOCHI {
		@Override
		public List<String> getSubcategory() {
			List<String> subcategory = new ArrayList<>();
			subcategory.add("Nintendo switch");
			subcategory.add("PS4");
			subcategory.add("Xbox One X");
			return subcategory;
		}

		@Override
		public String toString() {
			return "giochi";
		}
	};
	public abstract List<String> getSubcategory();

}
