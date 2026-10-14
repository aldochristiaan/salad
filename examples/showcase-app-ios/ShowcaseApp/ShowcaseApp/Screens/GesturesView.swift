import SwiftUI

/// A tappable/long-pressable card plus a color swatch that changes on swipe,
/// giving long-tap and swipe gestures observable targets.
struct GesturesView: View {
    @State private var presses = 0
    @State private var colorIndex = 0
    private let colors: [Color] = [.red, .green, .blue, .orange]

    var body: some View {
        VStack(spacing: 24) {
            Text("Presses: \(presses)")
                .accessibilityIdentifier("press_count")

            RoundedRectangle(cornerRadius: 12)
                .fill(colors[colorIndex % colors.count])
                .frame(height: 160)
                .overlay(Text("Swipe me").foregroundColor(.white))
                .accessibilityIdentifier("color_card")
                .gesture(
                    DragGesture(minimumDistance: 20)
                        .onEnded { _ in colorIndex += 1 }
                )

            Button("Long Press Me") { presses += 1 }
                .accessibilityIdentifier("long_press_button")
                .onLongPressGesture { presses += 1 }

            Spacer()
        }
        .padding()
        .navigationTitle("Gestures Screen")
    }
}

#Preview {
    NavigationStack { GesturesView() }
}
