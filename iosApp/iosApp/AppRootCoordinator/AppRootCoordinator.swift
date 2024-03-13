//
//  AppRootCoordinator.swift
//  swiftUItesting
//
//  Created by vlad.kosyi on 22.02.2024.
//

import Foundation
import Combine
import Swinject
import shared

@Observable
class AppRootCoordinator: ViewModel {
    
    private let resolver: Resolver

    private(set) var rootContentViewModel: UserListViewModel!

    let path = ObjectNavigationPath()

    init(resolver: Resolver) {
        self.resolver = resolver
        self.rootContentViewModel = self.resolver.resolve(UserListViewModel.self)!
          .setup(delegate: self)
    }
}

extension AppRootCoordinator: UserListViewDelegate {

    func userListViewModelDidOndetails(_ user: GitUser) {
        self.path.append(self.resolver.resolve(UserDetailViewModel.self)!
            .setup(user: user))
    }
}

