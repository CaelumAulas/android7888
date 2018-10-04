package br.com.caelum.casadocodigo.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.activity.CarrinhoActivity;

public class RecebedorDeNotificacao extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String conteudo = recuperaConteudo(remoteMessage);

        PendingIntent procuracao = criaPendingIntent();

        Notification notification = criaNotificacaoCom(conteudo, procuracao);

        mostra(notification);

    }

    private void mostra(Notification notification) {
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        manager.notify(123, notification);
    }

    private PendingIntent criaPendingIntent() {
        return PendingIntent.getActivity(getApplicationContext(),
                    123,
                    new Intent(getApplicationContext(), CarrinhoActivity.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private String recuperaConteudo(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        return data.get("message");
    }

    private Notification criaNotificacaoCom(String conteudo, PendingIntent procuracao) {
        return new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(R.drawable.casadocodigo)
                    .setContentTitle("Nova mensagem")
                    .setContentText(conteudo)
                    .setContentIntent(procuracao)
                    .setAutoCancel(true)
                    .build();
    }
}
