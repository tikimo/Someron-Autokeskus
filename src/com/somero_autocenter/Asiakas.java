package com.somero_autocenter;

public class Asiakas {

	public String asiakasNumero;
	public String nimi;
	public Auto auto;

	public Asiakas(Auto auto) {
		this.auto = auto;
		
	}

		public String getAsiakasNumero() {
			return asiakasNumero;
		}
		
		public void setAsiakasNumero(String asiakasNumero) {
			this.asiakasNumero = asiakasNumero;
		}
		
		public String getNimi() {
			return nimi;
		}
		
		public void setNimi(String nimi) {
			this.nimi = nimi;
		}
		
		public Auto getAuto() {
			return auto;
		}
		
		public void setAuto(Auto auto) {
			this.auto = auto;
		}
}
