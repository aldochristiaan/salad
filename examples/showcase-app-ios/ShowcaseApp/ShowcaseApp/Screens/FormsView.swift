import SwiftUI

/// Text fields, a toggle and a submit button — exercises type, clear, tap and validation.
struct FormsView: View {
    @State private var name = ""
    @State private var email = ""
    @State private var agreed = false
    @State private var submitted = false

    var body: some View {
        Form {
            Section {
                TextField("Name", text: $name)
                    .accessibilityIdentifier("name_field")
                TextField("Email", text: $email)
                    .keyboardType(.emailAddress)
                    .accessibilityIdentifier("email_field")
                Toggle("I agree to the terms", isOn: $agreed)
                    .accessibilityIdentifier("agree_toggle")
            }
            Section {
                Button("Submit") { submitted = true }
                    .accessibilityIdentifier("submit_button")
                Text(submitted ? "Submitted: \(name) / \(email)" : "Nothing submitted yet")
                    .accessibilityIdentifier("submit_result")
            }
        }
        .navigationTitle("Forms Screen")
    }
}

#Preview {
    NavigationStack { FormsView() }
}
