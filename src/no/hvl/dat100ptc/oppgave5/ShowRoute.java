package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		
		return ystep;		
	}

	public void showRouteMap(int ybase) {
		
		double[] nx = new double[gpspoints.length];
		double[] ny = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			nx[i] = gpspoints[i].getLongitude();
			ny[i] = gpspoints[i].getLatitude();
			drawCircle((int)nx[i], (int)ny[i], 10);
		}

		setColor(0,250,0);
		
	
		
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		

		setColor(0,0,0);
		setFont("Courier",12);
		
		int time = gpscomputer.totalTime();
		double dist = gpscomputer.totalDistance();
		double eleve = gpscomputer.totalElevation();
		double mspeed = gpscomputer.maxSpeed();
		double aspeed = gpscomputer.averageSpeed();
		double kcal = gpscomputer.totalKcal(80);
		
		String txt;

				
		
		
		}
	

}
