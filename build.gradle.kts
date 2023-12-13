plugins {
    id("com.google.dagger.hilt.android") version ("2.44.2") apply false
}
configurations.all {
    resolutionStrategy.force ("com.android.support:support-annotations:22.1.0")
}