import SwiftUI

private let appAssembler: AppAssembler = AppAssembler()

@main
struct iOSApp: App {
	var body: some Scene {
        WindowGroup {
          AppRootCoordinatorView(
            coordinator: appAssembler.resolver.resolve(AppRootCoordinator.self)!
          )
        }
	}
}
