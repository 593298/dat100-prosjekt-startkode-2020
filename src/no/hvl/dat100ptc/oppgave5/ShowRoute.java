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
	private static int MAPYSIZE = 600;

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
		double nxmin = 0;
		double nymin = 0;
		for(int i = 0; i < gpspoints.length ; i++) {
			
			nx[i] = gpspoints[i].getLongitude();
			ny[i] = gpspoints[i].getLatitude();	
			
			nxmin =	GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
			nymin =	GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
			
			double x = nx[i] - nxmin;
			double y = ny[i] - nymin;
			
			
			setColor(0,250,0);
			fillCircle((MARGIN) + (int) (x * xstep()), ybase -(int) (y * ystep()), 5);
			
		}

		
		
	
		
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		

		setColor(0,0,0);
		setFont("Courier",12);
		
		String time = "Total Time     :" + GPSUtils.formatTime(gpscomputer.totalTime())+ " ";
		String dist = "Total distance :" + GPSUtils.formatDouble(gpscomputer.totalDistance()/1000) + " km ";
		String eleve = "Total elevation:"+ GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m ";
		String mspeed = "Max speed      :" + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t ";
		String aspeed = "Average speed  :" + GPSUtils.formatDouble(gpscomputer.averageSpeed())+ " km/t ";
		String kcal = "Energy         :" + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + " kcal ";
		
		String[] tab = {time, dist, eleve, mspeed, aspeed, kcal};
		
		for(int  i = 1; i <= tab.length; i++ ) {		
			
			drawString(tab[i-1], 10, i*TEXTDISTANCE);
	
			
		}
	}
	

}