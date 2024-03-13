//
//  UserDetailView.swift
//  swiftUItesting
//
//  Created by vlad.kosyi on 23.02.2024.
//

import SwiftUI

struct UserDetailView: View {

    @State var viewModel: UserDetailViewModel

    var body: some View {

        let size = UIScreen.main.bounds.width/1.5

        AsyncImage(url: URL(string: viewModel.userImageUrl)) { phase in
            switch phase {
            case .empty:
                ProgressView()
            case .success(let image):
                image.resizable()
                    .aspectRatio(contentMode: .fit)
                    .clipShape(Circle())
                    .frame(maxWidth: size, maxHeight: size)
            case .failure:
                Image(systemName: "smiley")
                    .resizable()
                    .clipShape(Circle())
                    .frame(maxWidth: size, maxHeight: size)
            @unknown default:
                EmptyView()
            }
        }.padding(.top, 100)

        VStack(alignment: .center) {
            Text(self.viewModel.userName)
                .multilineTextAlignment(.center)
            Text("\(self.viewModel.userUrl)")
                .font(.subheadline)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
        }.padding()

        Spacer()
    }
}

#if DEBUG
struct UserDetailView_Previews: PreviewProvider {
  static let appAssembler = AppAssembler()
  static let viewModel = appAssembler.resolver.resolve(UserDetailViewModel.self)!

  static var previews: some View {
    Group {
        UserDetailView(viewModel: viewModel)
        .edgesIgnoringSafeArea(.all)
    }
  }
}
#endif
