import SwiftUI

/// Home screen with a navigation link to each showcase screen.
/// Accessibility identifiers are stable so Appium can locate them by name.
struct ContentView: View {
    var body: some View {
        NavigationStack {
            List {
                NavigationLink("List Screen") { ListView() }
                NavigationLink("Forms Screen") { FormsView() }
                NavigationLink("Toast Screen") { ToastView() }
                NavigationLink("Gestures Screen") { GesturesView() }
            }
            .navigationTitle("Salad Showcase")
            .accessibilityIdentifier("main_list")
        }
        .onOpenURL { url in
            // Handles saladshowcase:// deeplinks
            _ = url
        }
    }
}

#Preview {
    ContentView()
}
