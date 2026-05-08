# Lab 17 : ReceiverDemo - Apprentissage des BroadcastReceivers

Ce projet est une démonstration pratique de l'utilisation des `BroadcastReceiver` sous Android, couvrant les types statiques et dynamiques, ainsi que les broadcasts personnalisés.

## Objectifs du Lab
*   Apprendre à créer et utiliser les **BroadcastReceiver**.
*   Utiliser un **Receiver dynamique** pour détecter le changement de mode avion (`ACTION_AIRPLANE_MODE_CHANGED`).
*   Utiliser un **Receiver statique** pour `BOOT_COMPLETED` (démarrage du téléphone).
*   Envoyer et recevoir un **Broadcast personnalisé** depuis une Activity.
*   Comprendre le cycle de vie (`onReceive`), les permissions et les restrictions Android modernes (Android 14/15/16).

## Architecture du Projet

### 1. AirplaneModeReceiver (Dynamique)
Détecte si le mode avion est activé ou désactivé. Il est enregistré dynamiquement dans `MainActivity` pour respecter les bonnes pratiques de gestion des ressources système.

### 2. BootReceiver (Statique)
Déclaré dans le `AndroidManifest.xml`. Il nécessite la permission `RECEIVE_BOOT_COMPLETED`. Il affiche un Toast lorsque l'appareil a fini de démarrer.

### 3. CustomEventReceiver (Personnalisé)
Démontre comment une application peut envoyer ses propres événements internes via `sendBroadcast`.

## Fonctionnalités
*   **Activer/Désactiver Receiver Avion** : Permet d'enregistrer ou de désenregistrer le récepteur de mode avion en temps réel.
*   **Envoyer Custom Broadcast** : Déclenche un événement interne capturé par le `CustomEventReceiver`.

## Restrictions Android Modernes
*   **Enregistrement Dynamique** : Depuis Android 14, il est obligatoire de spécifier si un receiver dynamique est exporté ou non (`Context.RECEIVER_NOT_EXPORTED`).
*   **Broadcasts Implicites** : La plupart des broadcasts système ne peuvent plus être reçus via un enregistrement statique dans le Manifest (d'où l'usage du mode dynamique pour le mode avion).

## Comment tester ?
1.  **Mode Avion** : Cliquez sur "Activer Receiver Avion", puis basculez le mode avion dans les réglages rapides d'Android.
2.  **Custom Broadcast** : Cliquez sur le bouton dédié pour voir le message s'afficher.
3.  **Boot** : Pour tester le `BootReceiver`, redémarrez l'émulateur ou utilisez la commande ADB suivante :
    ```bash
    adb shell am broadcast -a android.intent.action.BOOT_COMPLETED
    ```
