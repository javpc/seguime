package pc.javier.seguime.control.difusion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import pc.javier.seguime.adaptador.Preferencias;
import pc.javier.seguime.control.ServicioAplicacion;

public class SmsReceptor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Preferencias preferencias = new Preferencias(context);
        if (preferencias.getPermitirActivarSMS () == false)
            return;

        Bundle intentExtras = intent.getExtras();
        if (intentExtras == null)
            return;

        Object[] sms = (Object[]) intentExtras.get("pdus");
        if (sms == null)
            return;
        for (Object sm : sms) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sm);

            String smsMensaje = smsMessage.getMessageBody().toString();
            String smsRemitente = smsMessage.getOriginatingAddress();

            if (smsMensaje.toLowerCase().trim().equals("seguime")) {
                preferencias.setNumeroSms(smsRemitente);
                preferencias.setRastreo(true);
                Intent servicio = new Intent(context, ServicioAplicacion.class);
                context.startService(servicio);
            }


        }

    }
}
