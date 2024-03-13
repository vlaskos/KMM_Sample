//
//  UserListViewModel.swift
//  swiftUItesting
//
//  Created by vlad.kosyi on 22.02.2024.
//

import Foundation
import Combine
import CombineExt
import shared

protocol UserListViewDelegate: AnyObject {
    func userListViewModelDidOndetails(_ user: GitUser)
}

@Observable
class UserListViewModel: ViewModel, ObservableObject {
    private weak var delegate: UserListViewDelegate?
    private var cancelBag: CancelBag!
    let onDetail: PassthroughSubject<GitUser?, Never> = PassthroughSubject()

    let sharedService = SharedService()

    var users: [GitUser] = []


    func setup(delegate: UserListViewDelegate) -> Self {
      self.delegate = delegate
      bind()
      return self
    }

    private func bind() {
        self.cancelBag = CancelBag()

        self.onDetail
            .sink { [weak self] user in
                guard let self = self, let user = user else { return }
                self.delegate?.userListViewModelDidOndetails(user)
            }
            .store(in: &self.cancelBag)
    }

    @MainActor
    func fetchUsers() async {
        Task {
           do {
               self.users = try await self.sharedService.fetchUsers()
               print(self.users)
           } catch {
               print(error)
           }
       }
    }
}
