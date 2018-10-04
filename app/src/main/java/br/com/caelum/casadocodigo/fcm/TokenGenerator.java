package br.com.caelum.casadocodigo.fcm;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.greenrobot.eventbus.EventBus;

public class TokenGenerator {

    public void generate() {

        FirebaseInstanceId instance = FirebaseInstanceId.getInstance();

        instance.getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            InstanceIdResult result = task.getResult();
                            EventBus.getDefault().post(result);

                        }
                    }
                });

    }
}
