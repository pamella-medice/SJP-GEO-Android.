package br.gov.demo.sjpgeo;
import android.Manifest;import android.app.*;import android.content.*;import android.content.pm.PackageManager;import android.location.*;import android.os.*;
public class LocationService extends Service {
 static final String CHANNEL="sjp_geo_tracking"; LocationManager manager; LocationListener listener; String tech="";
 @Override public void onCreate(){super.onCreate();NotificationChannel c=new NotificationChannel(CHANNEL,"Localização durante o turno",NotificationManager.IMPORTANCE_LOW);getSystemService(NotificationManager.class).createNotificationChannel(c);}
 @Override public int onStartCommand(Intent intent,int flags,int id){if(intent!=null)tech=intent.getStringExtra("technician");if(tech==null)tech="";
 Notification n=new Notification.Builder(this,CHANNEL).setContentTitle("SJP GEO — turno em andamento").setContentText("Localização ativa para "+tech+" (demonstração local)").setSmallIcon(android.R.drawable.ic_menu_mylocation).setOngoing(true).build();startForeground(12,n);
 manager=(LocationManager)getSystemService(LOCATION_SERVICE);if(listener!=null)manager.removeUpdates(listener);
 listener=loc->{getSharedPreferences("sjpgeo_gps",MODE_PRIVATE).edit().putString("technician",tech).putString("last_position",loc.getLatitude()+","+loc.getLongitude()).putLong("timestamp",System.currentTimeMillis()).apply();};
 try{if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,15,listener,Looper.getMainLooper());}else if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,15000,15,listener,Looper.getMainLooper());}}catch(Exception e){}return START_STICKY;}
 @Override public void onDestroy(){if(manager!=null&&listener!=null)manager.removeUpdates(listener);super.onDestroy();}
 @Override public IBinder onBind(Intent i){return null;}
}
