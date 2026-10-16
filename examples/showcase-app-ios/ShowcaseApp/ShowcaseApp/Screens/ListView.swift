import SwiftUI

/// A long scrollable list so swipe/scroll/fling gestures have somewhere to act.
struct ListView: View {
    private let items = Array(1...40)

    var body: some View {
        List(items, id: \.self) { item in
            Text("Item \(item)")
                .accessibilityIdentifier("list_item_\(item)")
        }
        .navigationTitle("List Screen")
    }
}

#Preview {
    NavigationStack { ListView() }
}
