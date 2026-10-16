import SwiftUI

/// A button that shows a short-lived banner, so toast detection has a target.
/// The result text stays visible so tests can assert on it deterministically.
struct ToastView: View {
    @State private var banner: String?
    @State private var lastToast = "No toast yet"

    var body: some View {
        VStack(spacing: 16) {
            Button("Show Toast") {
                banner = "This is a toast message"
                lastToast = banner ?? ""
                DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                    if banner == "This is a toast message" { banner = nil }
                }
            }
            .accessibilityIdentifier("toast_button")

            Text("Last toast: \(lastToast)")
                .accessibilityIdentifier("toast_result")

            Spacer()
        }
        .padding()
        .overlay(alignment: .top) {
            if let banner {
                Text(banner)
                    .padding()
                    .background(.black.opacity(0.8))
                    .foregroundColor(.white)
                    .cornerRadius(8)
                    .accessibilityIdentifier("toast_banner")
                    .padding(.top, 8)
            }
        }
        .navigationTitle("Toast Screen")
    }
}

#Preview {
    NavigationStack { ToastView() }
}
