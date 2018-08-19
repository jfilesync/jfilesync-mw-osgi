package net.workingdevelopers.jfilesync.mw.core.ui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.workingdeveloper.jfilesync.mw.core.ui.api.JavaFXRootStageProvider;

public class JfsUiActivator implements BundleActivator {
	public static boolean started = false;
	ServiceTracker<JavaFXRootStageProvider, JavaFXRootStageProvider> mainStageTracker;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("JfsCoreUI Bundle started");

		mainStageTracker = new ServiceTracker<>(context, JavaFXRootStageProvider.class,
				new ServiceTrackerCustomizer<JavaFXRootStageProvider, JavaFXRootStageProvider>() {

					@Override
					public JavaFXRootStageProvider addingService(ServiceReference<JavaFXRootStageProvider> reference) {
						// TODO Auto-generated method stub
						System.out.println("service published");
						JavaFXRootStageProvider service = context.getService(reference);
						Stage primaryStage = service.getStage();
						primaryStage.setTitle("Tabs example!");

						VBox vbox = new VBox();
						javafx.scene.control.Button button = new Button("Exit");
						vbox.getChildren().add(button);
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								System.out.println("Hello World!");
								try {
									context.getBundle(0).stop();
								} catch (BundleException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						primaryStage.setScene(new Scene(vbox, 300, 250));
						primaryStage.show();
						return service;
					}

					@Override
					public void modifiedService(ServiceReference<JavaFXRootStageProvider> reference,
							JavaFXRootStageProvider service) {
						// TODO Auto-generated method stub

					}

					@Override
					public void removedService(ServiceReference<JavaFXRootStageProvider> reference,
							JavaFXRootStageProvider service) {
						// TODO Auto-generated method stub
						System.out.println("service UNpuiblished");
					}
				});
		mainStageTracker.open();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		mainStageTracker.close();
		System.out.println("JfsCoreUI Bundle stopped");
	}

}
