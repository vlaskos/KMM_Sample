//
//  ViewModelAssembly.swift
//  MVVM.Demo.SwiftUI
//
//  Created by Jason Lew-Rapai on 11/15/21.
//

import Foundation
import Swinject

class ViewModelAssembly: Assembly {
  func assemble(container: Container) {

      container.register(UserListViewModel.self) { r in
          UserListViewModel()
      }.inObjectScope(.transient)

      container.register(UserDetailViewModel.self) { r in
          UserDetailViewModel()
      }.inObjectScope(.transient)
  }
}
