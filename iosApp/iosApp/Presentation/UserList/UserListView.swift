//
//  UserList.swift
//  swiftUItesting
//
//  Created by vlad.kosyi on 22.02.2024.
//

import SwiftUI

struct UserListView: View {

    @State var viewModel: UserListViewModel

    var body: some View {
        List {
            ForEach(self.viewModel.users, id: \.id) { user in
                HStack {

                    AsyncImage(url: URL(string: user.avatarURL)) { phase in
                        switch phase {
                        case .empty:
                            ProgressView()
                        case .success(let image):
                            image.resizable()
                                .aspectRatio(contentMode: .fit)
                                .clipShape(Circle())
                                .frame(maxWidth: 60, maxHeight: 60)
                        case .failure:
                            Image(systemName: "smiley")
                                .resizable()
                                .clipShape(Circle())
                                .frame(maxWidth: 60, maxHeight: 60)
                        @unknown default:
                            EmptyView()
                        }
                    }

                    VStack(alignment: .leading) {
                        Text(user.login)
                        Text("\(user.url)")
                            .font(.subheadline)
                            .foregroundColor(.secondary)
                    }
                    .padding()
                }
                .listRowSeparator(.hidden)
                .onTapGesture {
                    self.viewModel.onDetail.send(user)
                }
            }


        }
        .padding(.top, 30)
        .task {
            await viewModel.fetchUsers()
        }
    }
}

#if DEBUG
struct UserListView_Previews: PreviewProvider {
    static let appAssembler = AppAssembler()
    static let viewModel = appAssembler.resolver.resolve(UserListViewModel.self)!

    static var previews: some View {
        Group {
            UserListView(viewModel: viewModel)
                .edgesIgnoringSafeArea(.all)
        }
    }
}
#endif

