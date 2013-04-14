package phase2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regle {
	private int minFreq;
	private int minConf;
	private List<Motif> arrayMotifFreq;

	public Regle(int minConf, int minFreq) {
		this.minConf = minConf;
		this.minFreq = minFreq;
	}

	public void Extraction(String ficOut) throws IOException {
		// lecture du fichier green.out
		String fichier = ficOut;
		String hyphen = "-";
		List<Motif> arrayMotifFreq = new ArrayList<Motif>();
		InputStream ips = new FileInputStream(fichier);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);

		String ligneMotif;
		String space = " ";
		while ((ligneMotif = br.readLine()) != null) {
			Motif motif = new Motif();
			// On d�compose la ligne du motif dans un tableau
			String[] ligneDecomp = ligneMotif.split(space);
			// On transforme le tableau en arrayList
			ArrayList<String> listDecomp = new ArrayList<String>(
					Arrays.asList(ligneDecomp));

			for (int i = 0; i < listDecomp.size(); ++i) {
				String supp = listDecomp.get(i);
				// On cherche le nombre entre les parenth�ses : le support
				Pattern p = Pattern.compile("\\((.*)\\)");
				Matcher m = p.matcher(supp);

				if (m.find()) {
					supp = m.group(1);
					motif.setSupport(Integer.parseInt(supp));
					// On enl�ve le dernier �l�ment de l'arraylist (= le
					// support)
					listDecomp.remove(listDecomp.size() - 1);
					String mot = "";
					for (int j = 0; j < listDecomp.size(); ++j) {
						// On r�cup�re le motif
						mot += listDecomp.get(j) + hyphen;
					}
					motif.setVal(mot);
					arrayMotifFreq.add(motif);
					// System.out.println(motif);
				}

			} // for()

		} // while ()
		br.close();
		System.out.println(arrayMotifFreq);

		// Recherche de l'indice du premier motif dont le nombre d'attribut est > 1
		int posMotif = 0;
		for(int i = 0; i < arrayMotifFreq.size(); ++i) {
			String[] motSplit = arrayMotifFreq.get(i).getVal().split(hyphen);
			if (motSplit.length > 1) {
				posMotif = i;
				break;
			}
		}
		System.out.println(posMotif);
		
		for (int i = posMotif; i <arrayMotifFreq.size(); ++i){
			
		}
		
	} // Extraction ()

	public static void main(String[] args) throws IOException {
		Regle ER = new Regle(1, 1);
		ER.Extraction("green_test.out");

	} // main ()

} // class Regle
